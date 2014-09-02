package org.anotanota.model;

import java.util.List;

import com.google.common.util.concurrent.ListenableFuture;

public interface ReceiptsDataAccess {
  ListenableFuture<List<Receipt>> listReceipts();

  ListenableFuture<Receipt> getReceipt(int id);

  ListenableFuture<Void> deleteReceipt(int id);

  ListenableFuture<Void> updateReceipt(Receipt receipt);

  ListenableFuture<Void> createReceipt(Receipt receipt);
}
