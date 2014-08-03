package org.anotanota.sqlite;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.anotanota.framework.ActivityModule;
import org.anotanota.model.AggregatedProductsDataAccess;
import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false, addsTo = ActivityModule.class)
public class SQLiteModule {
  @Provides
  List<SQLiteDataAccessModule> dataSources(Context context) {
    return Arrays.<SQLiteDataAccessModule> asList(new SQLiteDataAccessModule(
        new SQLiteDataAccessConfig("ademirao"), context),
        new SQLiteDataAccessModule(new SQLiteDataAccessConfig("danielavvaz"),
            context));
  }

  @Module(library = true, complete = false, addsTo = SQLiteModule.class)
  public static class SQLiteDataAccessModule {
    private final SQLiteDataAccessConfig mConfig;
    private final Context mContext;

    public SQLiteDataAccessModule(SQLiteDataAccessConfig config, Context context) {
      mContext = context;
      mConfig = config;
    }

    @Provides
    Context context() {
      return mContext;
    }

    @Provides
    SQLiteDataAccessConfig getConfig() {
      return mConfig;
    }

    @Provides
    ReceiptItemsDataAccess itemsDataAccess(SQLiteReceiptItemsDataAccess dataAccess) {
      return dataAccess;
    }

    @Provides
    AggregatedProductsDataAccess productsDataAccess(SQLiteAggregatedProductsDataAccess productsDataAccess) {
      return productsDataAccess;
    }

    @Provides
    ReceiptsDataAccess receiptsDataAccess(SQLiteReceiptsDataAccess dataAccess) {
      return dataAccess;
    }

    @Provides
    @Named("DataAccessName")
    public String getName() {
      return mConfig.getUserName();
    }
  }
}
