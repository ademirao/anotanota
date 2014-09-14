package org.anotanota;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.anotanota.framework.UIViewController;
import org.anotanota.model.Receipt;
import org.anotanota.pipeline.AnotanotaPipeline.FullPipeline;
import org.anotanota.pipeline.framework.Pipeline;
import org.anotanota.views.ArrayAdapterHelper;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Function;

public class AddReceiptViewController implements UIViewController {
  private final ActionBar mActionBar;
  private final Context mContext;
  private final LayoutInflater mLayoutInflater;
  private final File[] mSelectedPaths;
  private final Pipeline mPipeline;
  private final ArrayAdapterHelper mArrayAdapterHelper;

  @Inject
  public AddReceiptViewController(Context context,
    ActionBar actionBar,
    ArrayAdapterHelper arrayAdapterBuilder,
    LayoutInflater layoutInflater,
    @Anotanota.SelectedPaths File[] selectedPaths,
    @FullPipeline Pipeline pipeline) {
    mSelectedPaths = selectedPaths;
    mActionBar = actionBar;
    mContext = context;
    mLayoutInflater = layoutInflater;
    mPipeline = pipeline;
    mArrayAdapterHelper = arrayAdapterBuilder;
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
    final ArrayAdapterHelper.Builder<File, LinearLayout> builder = mArrayAdapterHelper
        .<File, LinearLayout> newBuilder()
        .setItemLayout(R.layout.receipts_files)
        .setLoadItemFunction(new Function<Pair<LinearLayout, File>, Void>() {

          @Override
          public Void apply(Pair<LinearLayout, File> arg0) {
            loadFilePathView(arg0.second, arg0.first);
            return null;
          }
        });
    final SwipeRefreshLayout view = (SwipeRefreshLayout) mLayoutInflater
        .inflate(R.layout.swipe_to_refresh_list, null);
    final ListView filesList = (ListView) view.findViewById(R.id.list);
    final Runnable loadData = new Runnable() {
      @Override
      public void run() {
        view.setRefreshing(true);
        final List<File> files = new ArrayList<File>();

        listFiles(mSelectedPaths, files);
        filesList.setAdapter(builder.setObjects(files).get());
        view.setRefreshing(false);
      }

    };
    view.setOnRefreshListener(new OnRefreshListener() {

      @Override
      public void onRefresh() {
        loadData.run();
      }
    });
    loadData.run();
    return view;
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
        mPipeline.produce(Receipt.class);
        Toast.makeText(mContext, "Ocring " + file.getAbsolutePath() + " ...",
            Toast.LENGTH_SHORT).show();
      }

    });
  }
}
