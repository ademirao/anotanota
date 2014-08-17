package org.anotanota.pipeline;

import java.io.File;

import org.anotanota.AnotanotaActivityModule.DataAccessScopedEndpoints;
import org.anotanota.framework.pipeline.Pipeline;
import org.anotanota.framework.pipeline.Product;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptsDataAccess;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.googlecode.tesseract.android.TessBaseAPI;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = { Pipeline.PipelineModule.class }, addsTo = DataAccessScopedEndpoints.class)
public class ReceiptProducer {
  @SuppressLint("NewApi")
  @Provides
  @Product
  Receipt getResult(@Pipeline.InputFile File file,
                    ReceiptsDataAccess receiptsDA,
                    TessBaseAPI tesseract) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    options.inPurgeable = true;
    System.out.println("File size " + file.length());
    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    System.out.println("Bitmap size " + bitmap.getByteCount());
    tesseract.setImage(bitmap);
    String recognizedText = tesseract.getUTF8Text();
    tesseract.end();
    Receipt receipt = new Receipt.Builder().setContent(recognizedText)
        .setPath(file.getAbsolutePath()).get();
    receiptsDA.createReceipt(receipt);
    return receipt;
  }
}
