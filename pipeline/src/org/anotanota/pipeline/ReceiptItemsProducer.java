package org.anotanota.pipeline;

import java.util.regex.Pattern;

import org.anotanota.framework.pipeline.Product;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;

import com.google.common.base.Splitter;

import dagger.Module;
import dagger.Provides;

@Module(library = true, injects = ReceiptItem.class, includes = ReceiptItemsProducer.Inputs.class, overrides = true)
public class ReceiptItemsProducer {

  @Module(library = true)
  public static class Inputs {
    @Product
    @Provides
    Receipt getReceipt() {
      return null;
    }

    @Product
    @Provides
    ReceiptItemsDataAccess getDa() {
      return null;
    }
  }

  @Provides
  @Product
  ReceiptItem getReceiptItems(@Product Receipt receipt,
                              ReceiptItemsDataAccess itemsDA) {
    String content = receipt.getContent();
    Iterable<String> lines = Splitter.on(Pattern.compile("\r?\n")).split(
        content);
    for (String line : lines) {
      ReceiptItem item = new ReceiptItem.Builder().setContent(line)
          .setReceiptId(receipt.getId()).get();
      itemsDA.createItem(item);
    }
    return null;
  }
}
