package org.anotanota;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Provider;

import org.anotanota.framework.UIViewController;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

public class AddReceiptItemsViewController implements UIViewController {
  private final ActionBar mActionBar;
  private final Provider<ListView> mFilesListProvider;
  private final Context mContext;
  private final LayoutInflater mLayoutInflater;
  private final AssetManager mAssetManager;
  private final ThreadPoolExecutor mPool;
  private final ReceiptsDataAccess mReceiptsDataAccess;
  private final ReceiptItemsDataAccess mReceiptItemsDataAccess;

  private static final List<String> kPhotosFolders = Arrays
      .asList("tesseract/examples");

  @Inject
  public AddReceiptItemsViewController(Context context,
    ActionBar actionBar,
    AssetManager assets,
    Provider<ListView> listView,
    LayoutInflater layoutInflater,
    ReceiptsDataAccess receiptsDataAccess,
    ReceiptItemsDataAccess receiptItemsDataAccess,
    @Anotanota.OCRThread ThreadPoolExecutor ocrThreadPool) {
    mActionBar = actionBar;
    mFilesListProvider = listView;
    mContext = context;
    mLayoutInflater = layoutInflater;
    mAssetManager = assets;
    mPool = ocrThreadPool;
    mReceiptsDataAccess = receiptsDataAccess;
    mReceiptItemsDataAccess = receiptItemsDataAccess;
  }

  public static String ocrReceipt(String filePath) {
    TessBaseAPI baseApi = new TessBaseAPI();
    // DATA_PATH = Path to the storage
    // lang = for which the language data exists, usually "eng"
    baseApi.init("/mnt/sdcard/tesseract", "por");
    baseApi.ReadConfigFile("/mnt/sdcard/tesseract/config");

    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
    baseApi.setImage(bitmap);
    String recognizedText = baseApi.getUTF8Text();
    baseApi.end();

    return recognizedText;
  }

  @Override
  public View loadView() {
    ListView filesList = this.mFilesListProvider.get();
    final List<File> filePaths = new ArrayList<>();

    for (String folder : kPhotosFolders) {
      String path = Environment.getExternalStorageDirectory().toString() + "/"
          + folder;
      System.out.println(path);
      File currentDir = new File(path);
      File[] files = currentDir.listFiles();
      if (files == null)
        continue;

      for (File file : files) {
        filePaths.add(file);
      }
    }

    filesList.setAdapter(new ArrayAdapter<File>(mContext,
        R.layout.receipts_files, filePaths) {
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout receiptItemView = null;
        if (convertView == null) {
          receiptItemView = createFilePathView(null);
        } else {
          receiptItemView = (LinearLayout) convertView;
        }
        loadFilePathView(filePaths.get(position), receiptItemView);
        return receiptItemView;
      }
    });
    return filesList;
  }

  private final LinearLayout createFilePathView(ViewGroup parent) {
    LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(
        R.layout.receipts_files, parent);
    return linearLayout;
  }

  private static List<ReceiptItem> findItems(String text) {
    // TODO Auto-generated method stub
    return null;
  }

  private final void loadFilePathView(File file, LinearLayout view) {
    TextView textView = ((TextView) view.findViewById(R.id.path));
    final String path = file.getAbsolutePath();
    textView.setText(path);
    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(mContext, "Ocring..." + path, Toast.LENGTH_LONG).show();
        mPool.execute(new Runnable() {

          @Override
          public void run() {
            String text = ocrReceipt(path);
            Receipt receipt = new Receipt.Builder().setPath(path)
                .setContent(text).get();
            mReceiptsDataAccess.createReceipt(receipt);
          }

        });
      }
    });
  }
}
