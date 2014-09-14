package org.anotanota;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Provider;
import javax.inject.Singleton;

import org.anotanota.framework.Activity;
import org.anotanota.framework.App;
import org.anotanota.framework.UIViewController;
import org.anotanota.framework.drawer.NavigationDrawer;
import org.anotanota.framework.drawer.NavigationDrawerViewController;
import org.anotanota.framework.pager.Pager;
import org.anotanota.framework.pager.PagerAdapter;
import org.anotanota.pipeline.AnotanotaPipeline;
import org.anotanota.pipeline.AnotanotaPipelineModule;
import org.anotanota.pipeline.OCR;
import org.anotanota.sqlite.SQLiteModule;
import org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule;
import org.dagger.scope.Scope;
import org.dagger.scope.ScopeModule;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.googlecode.tesseract.android.TessBaseAPI;

import dagger.Module;
import dagger.Provides;

@Module(includes = { SQLiteModule.class, ScopeModule.class,
    AnotanotaPipelineModule.class }, library = true, addsTo = AnotanotaModule.class)
public class AnotanotaActivityModule {

  @Provides
  @App.MainViewController
  UIViewController getMainVC(MainViewController vc) {
    return vc;
  }

  @Provides
  @NavigationDrawer.Titles
  List<String> titles(List<SQLiteDataAccessModule> dataAccesses) {
    List<String> titles = new ArrayList<String>();
    for (SQLiteDataAccessModule module : dataAccesses) {
      titles.add(module.getName());
    }
    return titles;
  }

  @Provides
  @NavigationDrawer.ViewControler
  public UIViewController drawerVC(Scope scope,
                                   Context context,
                                   NavigationDrawerViewController drawer,
                                   List<SQLiteDataAccessModule> dataAccess) {
    return scope
        .newChild(dataAccess.get(drawer.getCurrentPosition()),
            new DataAccessScopedEndpoints()).getGraph()
        .get(DataSourceViewController.class);
  }

  @Module(injects = { DataSourceViewController.class }, includes = { SQLiteDataAccessModule.class }, library = true, addsTo = AnotanotaActivityModule.class)
  public static class DataAccessScopedEndpoints {

    @Provides
    @Pager.Titles
    List<String> tabsTitles() {
      return Arrays.asList("Produtos", "Items", "Notas", "Adicionar Nota");
    }

    @Provides
    @Pager.Tabs
    UIViewController tabsViewControllers(PagerAdapter adapter,
                                         Provider<AddReceiptViewController> addReceipts,
                                         Provider<ReceiptsItemsViewController> receiptsItems,
                                         Provider<ReceiptsViewController> receipts,
                                         Provider<AggregatedProductsViewController> aggregatedProducts) {
      List<Provider<? extends UIViewController>> asList = Arrays.asList(
          aggregatedProducts, receiptsItems, receipts, addReceipts);
      return asList.get(adapter.getCurrentTab()).get();
    }
  }

  @Provides
  @Singleton
  ThreadPoolExecutor getThreadPool() {
    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    return new ThreadPoolExecutor(4, 6, 24 * 60 * 60, TimeUnit.SECONDS, queue);
  }

  @Provides
  @Singleton
  ListeningExecutorService getService(ThreadPoolExecutor threadPool) {
    return MoreExecutors.listeningDecorator(threadPool);
  }

  @Provides
  @Anotanota.Views.TabsFrame
  ViewPager pagerView(Activity activity) {
    return (ViewPager) activity
        .findViewById(org.anotanota.framework.R.id.pager_view);
  }

  @Provides
  ListView listView(Activity activity) {
    return new ListView(activity);
  }

  @Provides
  TextView textView(Activity activity) {
    return new TextView(activity);
  }

  private static final String kTesseractInstalationDirPath = Environment
      .getExternalStorageDirectory().toString() + "/anotanota/tesseract";

  private static final String kTesseractConfigPath = Joiner.on(
      File.separatorChar).join(kTesseractInstalationDirPath, "config");

  @Provides
  @AnotanotaPipeline.TesseractPath
  String getInstallPath() {
    return kTesseractInstalationDirPath;
  }

  @Provides
  @Anotanota.TesseractAssetsPath
  String getAssetsPath() {
    return "tesseract";
  }

  @Provides
  @AnotanotaPipeline.TesseractConfig
  String getTesseractConfig() {
    return kTesseractConfigPath;
  }

  @Provides
  OCR getLoader(final TessBaseAPI tesseract,
                final ListeningExecutorService service) {
    return new OCR() {

      @Override
      public String getUTF8Text(final File file) {
        System.out.println(" GEt utf 8: " + file);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        System.out.println("File size " + file.length());
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),
            options);
        tesseract.setImage(bitmap);
        try {
          return tesseract.getUTF8Text();
        } finally {
          tesseract.end();
        }
      }
    };
  }

  @Provides
  @Anotanota.SelectedPaths
  File[] getSelectedPaths(@AnotanotaPipeline.TesseractPath String installPath) {
    return new File[] { new File(Joiner.on(File.separator).join(installPath,
        "examples")), };
  }
}
