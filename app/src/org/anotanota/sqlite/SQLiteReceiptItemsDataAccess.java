package org.anotanota.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class SQLiteReceiptItemsDataAccess extends SQLiteOpenHelper implements
    ReceiptItemsDataAccess {

  // Contacts table name
  private static final String TABLE_NAME = "items";

  // Contacts Table Columns names
  private static final String ID_FIELD = "id";
  private static final String NAME_FIELD = "name";
  private static final String PRICE_FIELD = "price";
  private static final String CONTENT_FIELD = "content";

  private static final String RECEIPT_ID_FIELD = "receipt_id";

  private final String mTableName;

  @Inject
  public SQLiteReceiptItemsDataAccess(Context context,
    SQLiteDataAccessConfig config) {

    super(context,
        config.getDatabaseName() + config.getUserName() + TABLE_NAME, null,
        config.getDatabaseVersion());
    mTableName = TABLE_NAME + "_" + config.getUserName();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_ITEMS_TABLE = "CREATE TABLE " + mTableName + "(" + ID_FIELD
        + " INTEGER PRIMARY KEY," + RECEIPT_ID_FIELD + " INTEGER," + NAME_FIELD
        + " TEXT," + CONTENT_FIELD + " TEXT," + PRICE_FIELD + " DOUBLE" + ")";
    db.execSQL(CREATE_ITEMS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
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
    Joiner joiner = Joiner.on(",").skipNulls();
    String[] fields = new String[] { ID_FIELD, NAME_FIELD, RECEIPT_ID_FIELD,
        PRICE_FIELD, CONTENT_FIELD };
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT " + joiner.join(fields) + " from "
        + mTableName, null);

    List<ReceiptItem> receipts = new ArrayList<ReceiptItem>();
    while (cursor != null && cursor.moveToNext()) {
      ReceiptItem receipt = new ReceiptItem.Builder().setId(cursor.getInt(0))
          .setName(cursor.getString(1)).setReceiptId(cursor.getInt(2))
          .setPrice(cursor.getFloat(3)).setContent(cursor.getString(4)).get();
      receipts.add(receipt);
    }

    cursor.close();
    db.close();

    return Futures.immediateFuture(receipts);
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
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(NAME_FIELD, item.getName());
    values.put(PRICE_FIELD, item.getPrice());
    values.put(RECEIPT_ID_FIELD, item.getReceiptId());
    values.put(CONTENT_FIELD, item.getContent());
    db.insert(mTableName, null, values);

    return Futures.immediateFuture(null);
  }
}
