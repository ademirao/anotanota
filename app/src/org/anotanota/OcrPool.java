package org.anotanota;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.common.base.Function;
import com.googlecode.tesseract.android.TessBaseAPI;

@Singleton
public class OcrPool {
  private final ThreadPoolExecutor mThreadPool;
  private final Provider<TessBaseAPI> mTesseract;

  @Inject
  public OcrPool(@Anotanota.OCRThread ThreadPoolExecutor threadPool,
    Provider<TessBaseAPI> tesseract) {
    mThreadPool = threadPool;
    mTesseract = tesseract;
  }

  public boolean ocr(final File file, final Function<String, Void> function) {
    mThreadPool.execute(new Runnable() {

      @Override
      public void run() {
        TessBaseAPI tesseract = mTesseract.get();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),
            options);
        tesseract.setImage(bitmap);
        String recognizedText = tesseract.getUTF8Text();
        tesseract.end();
        function.apply(recognizedText);
      }
    });
    return true;
  }

}
