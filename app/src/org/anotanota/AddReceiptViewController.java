package org.anotanota;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.anotanota.framework.UIViewController;
import org.anotanota.framework.pipeline.Pipeline;
import org.anotanota.model.Receipt;
import org.anotanota.pipeline.AnotanotaPipeline.FullPipeline;
import org.anotanota.pipeline.InputFileProducer;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;

public class AddReceiptViewController implements UIViewController {
  private final ActionBar mActionBar;
  private final Provider<ListView> mFilesListProvider;
  private final Context mContext;
  private final LayoutInflater mLayoutInflater;
  private final File[] mSelectedPaths;
  private final Object[] mProducers;
  private final Pipeline mPipeline;

  @Inject
  public AddReceiptViewController(Context context,
    ActionBar actionBar,
    Provider<ListView> listView,
    LayoutInflater layoutInflater,
    @Anotanota.SelectedPaths File[] selectedPaths,
    @FullPipeline Object[] producers,
    Pipeline pipeline) {
    mSelectedPaths = selectedPaths;
    mActionBar = actionBar;
    mFilesListProvider = listView;
    mContext = context;
    mLayoutInflater = layoutInflater;
    mPipeline = pipeline;
    mProducers = producers;
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
        mPipeline.produce(Receipt.class,
            Lists.<Object> asList(new InputFileProducer(file), mProducers));
        Toast.makeText(mContext, "Ocring " + file.getAbsolutePath() + " ...",
            Toast.LENGTH_SHORT).show();
      }

    });
  }
}
