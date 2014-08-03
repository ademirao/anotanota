package org.anotanota;

import javax.inject.Singleton;

import org.anotanota.model.ReceiptItemsDataAccess;
import org.anotanota.model.ReceiptsDataAccess;

@Singleton
public class Extractor {

  public Extractor(ReceiptsDataAccess receipts,
    ReceiptItemsDataAccess receiptItems) {
  }
}
