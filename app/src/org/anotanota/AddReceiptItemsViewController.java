package org.anotanota;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;
import javax.inject.Provider;

import org.anotanota.framework.UIViewController;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Function;

public class AddReceiptItemsViewController implements UIViewController {
  private final ActionBar mActionBar;
  private final Provider<ListView> mFilesListProvider;
  private final Context mContext;
  private final LayoutInflater mLayoutInflater;
  private final AssetManager mAssetManager;
  private final ThreadPoolExecutor mPool;
  private final ReceiptsDataAccess mReceiptsDataAccess;
  private final ReceiptItemsDataAccess mReceiptItemsDataAccess;
  private final OcrPool mOcrPool;
  private final File[] mSelectedPaths;

  @Inject
  public AddReceiptItemsViewController(Context context,
    ActionBar actionBar,
    AssetManager assets,
    Provider<ListView> listView,
    LayoutInflater layoutInflater,
    ReceiptsDataAccess receiptsDataAccess,
    ReceiptItemsDataAccess receiptItemsDataAccess,
    @Anotanota.OCRThread ThreadPoolExecutor ocrThreadPool,
    @Anotanota.SelectedPaths File[] selectedPaths,
    OcrPool ocrPool) {
    mSelectedPaths = selectedPaths;
    mOcrPool = ocrPool;
    mActionBar = actionBar;
    mFilesListProvider = listView;
    mContext = context;
    mLayoutInflater = layoutInflater;
    mAssetManager = assets;
    mPool = ocrThreadPool;
    mReceiptsDataAccess = receiptsDataAccess;
    mReceiptItemsDataAccess = receiptItemsDataAccess;
  }

  public void listFiles(File[] files, List<File> allFiles) {
    for (File file : files) {
      if (file.isDirectory()) {
        listFiles(file.listFiles(), allFiles);
        continue;
      }
      allFiles.add(file);
    }
  }

  @Override
  public View loadView() {
    ListView filesList = this.mFilesListProvider.get();
    final List<File> files = new ArrayList<File>();

    listFiles(mSelectedPaths, files);

    filesList.setAdapter(new ArrayAdapter<File>(mContext,
        R.layout.receipts_files, files) {
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout receiptItemView = null;
        if (convertView == null) {
          receiptItemView = createFilePathView(null);
        } else {
          receiptItemView = (LinearLayout) convertView;
        }
        loadFilePathView(files.get(position), receiptItemView);
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

  private final void loadFilePathView(final File file, LinearLayout view) {
    TextView textView = ((TextView) view.findViewById(R.id.path));
    textView.setText(file.getAbsolutePath());
    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final boolean scheduled = mOcrPool.ocr(file,
            new Function<String, Void>() {
              @Override
              public Void apply(String text) {
                Receipt receipt = new Receipt.Builder()
                    .setPath(file.getAbsolutePath()).setContent(text).get();
                mReceiptsDataAccess.createReceipt(receipt);
                return null;
              }
            });
        if (!scheduled) {
          Toast.makeText(mContext, "Could not ocr " + file.getAbsolutePath(),
              Toast.LENGTH_LONG).show();
          return;
        }
        Toast.makeText(mContext, "Ocring " + file.getAbsolutePath() + " ...",
            Toast.LENGTH_SHORT).show();
      }

    });
  }
}
