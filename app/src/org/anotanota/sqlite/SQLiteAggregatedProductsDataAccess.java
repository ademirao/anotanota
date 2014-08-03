package org.anotanota.sqlite;

import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.anotanota.model.AggregatedProduct;
import org.anotanota.model.AggregatedProductsDataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.util.concurrent.ListenableFuture;

public class SQLiteAggregatedProductsDataAccess extends SQLiteOpenHelper
    implements AggregatedProductsDataAccess {

  // All Static variables
  // Database Version
  private static final int DATABASE_VERSION = 1;

  // Database Name
  private static final String DATABASE_NAME = "anotanota";

  // Contacts table name
  private static final String TABLE_ITEMS = "aggregated_products";

  // Contacts Table Columns names
  private static final String KEY_PRODUCT_ID = "id";
  private static final String KEY_PRODUCT_NAME = "name";
  private static final String KEY_PRODUCT_PRICE = "price";

  @Inject
  public SQLiteAggregatedProductsDataAccess(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
        + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_ID
        + " INTEGER," + KEY_PRODUCT_NAME + " TEXT," + KEY_PRODUCT_PRICE
        + " DOUBLE" + ")";
    db.execSQL(CREATE_ITEMS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
    // Create tables again
    onCreate(db);
  }

  @Override
  public Future<AggregatedProduct> getProduct(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListenableFuture<List<AggregatedProduct>> listProducts() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> deleteProduct(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> updateProduct(AggregatedProduct item) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Future<Void> createProduct(AggregatedProduct item) {
    // TODO Auto-generated method stub
    return null;
  }

}
