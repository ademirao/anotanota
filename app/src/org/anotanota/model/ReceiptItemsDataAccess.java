package org.anotanota.model;

import java.util.List;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.ListenableFuture;

public interface ReceiptItemsDataAccess {

  Future<ReceiptItem> getItem(int id);

  ListenableFuture<List<ReceiptItem>> listItems();

  Future<List<ReceiptItem>> listItemsOfReceipt(int receiptId);

  Future<Void> deleteItem(int id);

  Future<Void> updateItem(ReceiptItem item);

  Future<Void> createItem(ReceiptItem item);
}
