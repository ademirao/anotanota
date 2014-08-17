package org.anotanota.pipeline;

import java.util.regex.Pattern;

import org.anotanota.framework.pipeline.Product;
import org.anotanota.model.Receipt;
import org.anotanota.model.ReceiptItem;
import org.anotanota.model.ReceiptItemsDataAccess;

import com.google.common.base.Splitter;

import dagger.Module;

@Module(library = true)
public class ReceiptItemsProducer {

  @Product
  Result getReceiptItems(@Product Receipt receipt,
                         ReceiptItemsDataAccess itemsDA) {
    String content = receipt.getContent();
    Iterable<String> lines = Splitter.on(Pattern.compile("\r?\n")).split(
        content);

    for (String line : lines) {
      ReceiptItem item = new ReceiptItem.Builder().setName(line).get();

    }
    return null;
  }
}
