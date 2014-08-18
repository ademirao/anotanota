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
import org.anotanota.framework.ActivityModule;
import org.anotanota.framework.App;
import org.anotanota.framework.Scope;
import org.anotanota.framework.UIViewController;
import org.anotanota.framework.drawer.NavigationDrawer;
import org.anotanota.framework.drawer.NavigationDrawerModule;
import org.anotanota.framework.drawer.NavigationDrawerViewController;
import org.anotanota.framework.pager.Pager;
import org.anotanota.framework.pager.PagerAdapter;
import org.anotanota.framework.pipeline.Pipeline;
import org.anotanota.sqlite.SQLiteModule;
import org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule;

import android.content.Context;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.googlecode.tesseract.android.TessBaseAPI;

import dagger.Module;
import dagger.Provides;

@Module(includes = { SQLiteModule.class, ActivityModule.class,
    NavigationDrawerModule.class }, library = true, addsTo = AnotanotaModule.class, overrides = true)
public class AnotanotaActivityModule {
  @Provides
  @App.MainViewController
  public UIViewController mainViewController(MainViewController controller) {
    return controller;
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
  @NavigationDrawer.Layout
  DrawerLayout drawerLayout(Activity activity) {
    return (DrawerLayout) activity.findViewById(R.id.drawer_layout);
  }

  @Provides
  @NavigationDrawer.View
  ListView drawerView(Activity activity) {
    return (ListView) activity.findViewById(R.id.left_drawer);
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
  @Pipeline.Executor
  ThreadPoolExecutor getThreadPool() {
    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    return new ThreadPoolExecutor(4, 6, 24 * 60 * 60, TimeUnit.SECONDS, queue);
  }

  @Provides
  @Singleton
  @Pipeline.Executor
  ListeningExecutorService getService(ThreadPoolExecutor threadPool) {
    return MoreExecutors.listeningDecorator(threadPool);
  }

  @Provides
  @Anotanota.Views.TabsFrame
  ViewPager pagerView(Activity activity) {
    return (ViewPager) activity.findViewById(R.id.pager_view);
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
  @Anotanota.TesseractInstallPath
  String getInstallPath() {
    return kTesseractInstalationDirPath;
  }

  @Provides
  @Anotanota.TesseractAssetsPath
  String getAssetsPath() {
    return "tesseract";
  }

  @Provides
  @Anotanota.TesseractConfig
  String getTesseractConfig() {
    return kTesseractConfigPath;
  }

  @Provides
  @Anotanota.SelectedPaths
  File[] getSelectedPaths(@Anotanota.TesseractInstallPath String installPath) {
    return new File[] { new File(Joiner.on(File.separator).join(installPath,
        "examples")), };
  }

  @Provides
  TessBaseAPI baseApi(@Anotanota.TesseractConfig String config,
                      @Anotanota.TesseractInstallPath String tesseractDir) {
    TessBaseAPI baseApi = new TessBaseAPI();
    baseApi.init(tesseractDir, "nota");
    baseApi.ReadConfigFile(config);
    return baseApi;
  }
}
