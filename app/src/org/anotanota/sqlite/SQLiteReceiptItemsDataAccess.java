package org.anotanota.sqlite;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;

import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class SQLiteReceiptItemsDataAccess extends SQLiteOpenHelper implements
    ReceiptItemsDataAccess {

  // All Static variables
  // Database Version
  private static final int DATABASE_VERSION = 1;

  // Database Name
  private static final String DATABASE_NAME = "anotanota";

  // Contacts table name
  private static final String TABLE_ITEMS = "items";

  // Contacts Table Columns names
  private static final String KEY_ITEM_ID = "id";
  private static final String KEY_ITEM_NAME = "name";
  private static final String KEY_ITEM_PRICE = "price";

  private static final String KEY_RECEIPT_ID = "receipt_id";

  private final String mTableName;

  @Inject
  public SQLiteReceiptItemsDataAccess(Context context,
    @Named("DataAccessName") String dataAccessName,
    SQLiteDataAccessConfig config) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    mTableName = TABLE_ITEMS + "_" + dataAccessName;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_ITEMS_TABLE = "CREATE TABLE " + mTableName + "("
        + KEY_ITEM_ID + " INTEGER PRIMARY KEY," + KEY_RECEIPT_ID + " INTEGER,"
        + KEY_ITEM_NAME + " TEXT," + KEY_ITEM_PRICE + " DOUBLE" + ")";
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
  public Future<ReceiptItem> getItem(int id) {
    return Futures.immediateFuture(new ReceiptItem.Builder().setName(
        "Item " + id).get());
  }

  @Override
  public ListenableFuture<List<ReceiptItem>> listItems() {
    return Futures.immediateFuture(Arrays.asList(new ReceiptItem.Builder()
        .setName(mTableName + "1").setPrice(1).setQuantity(10).get(),
        new ReceiptItem.Builder().setName(mTableName + "2").setPrice(2)
            .setQuantity(20).get()));
  }

  @Override
  public Future<List<ReceiptItem>> listItemsOfReceipt(int receiptId) {
    return listItems();
  }

  @Override
  public Future<Void> deleteItem(int id) {
    return Futures.immediateFuture(null);
  }

  @Override
  public Future<Void> updateItem(ReceiptItem item) {
    return Futures.immediateFuture(null);
  }

  @Override
  public Future<Void> createItem(ReceiptItem item) {
    // TODO Auto-generated method stub
    return null;
  }

}
