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

@Module(library = true, addsTo = ActivityModule.class)
public class SQLiteModule {
  @Provides
  List<SQLiteDataAccessModule> dataSources(Context context) {
    return Arrays.<SQLiteDataAccessModule> asList(new SQLiteDataAccessModule(
        new SQLiteDataAccessConfig("ademirao")), new SQLiteDataAccessModule(
        new SQLiteDataAccessConfig("danielavvaz")));
  }

  @Module(library = true, addsTo = SQLiteModule.class)
  public static class SQLiteDataAccessModule {
    private final SQLiteDataAccessConfig mConfig;

    public SQLiteDataAccessModule(SQLiteDataAccessConfig config) {
      mConfig = config;
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
