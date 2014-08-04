package org.anotanota.sqlite;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptsDataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class SQLiteReceiptsDataAccess extends SQLiteOpenHelper implements
    ReceiptsDataAccess {
  // Contacts table name
  private static final String TABLE_NAME = "receipts";

  // Contacts Table Columns names
  private static final String ID_FIELD = "id";
  private static final String PATH_FIELD = "name";
  private static final String CONTENT_FIELD = "price";

  private final String mTableName;

  @Inject
  public SQLiteReceiptsDataAccess(Context context, SQLiteDataAccessConfig config) {
    super(context,
        config.getDatabaseName() + config.getUserName() + TABLE_NAME, null,
        config.getDatabaseVersion());
    mTableName = TABLE_NAME;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_ITEMS_TABLE = "CREATE TABLE " + mTableName + "(" + ID_FIELD
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + PATH_FIELD + " TEXT,"
        + CONTENT_FIELD + " TEXT" + ")";
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
  public ListenableFuture<List<Receipt>> listReceipts() {
    Joiner joiner = Joiner.on(",").skipNulls();
    String[] fields = new String[] { ID_FIELD, PATH_FIELD, CONTENT_FIELD };
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT " + joiner.join(fields) + " from "
        + mTableName, null);

    List<Receipt> receipts = new ArrayList<>();
    while (cursor != null && cursor.moveToNext()) {
      Receipt receipt = new Receipt.Builder().setId(cursor.getInt(0))
          .setPath(cursor.getString(1)).setContent(cursor.getString(2)).get();
      receipts.add(receipt);
    }

    cursor.close();
    db.close();

    return Futures.immediateFuture(receipts);
  }

  @Override
  public ListenableFuture<Receipt> getReceipt(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListenableFuture<Void> deleteReceipt(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListenableFuture<Void> updateReceipt(Receipt receipt) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListenableFuture<Void> createReceipt(Receipt receipt) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(PATH_FIELD, receipt.getPath());
    values.put(CONTENT_FIELD, receipt.getContent());
    db.insert(mTableName, null, values);

    return Futures.immediateFuture(null);
  }

}
