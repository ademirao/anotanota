package org.anotanota.pipeline;

import org.anotanota.framework.pipeline.Product;
import org.anotanota.model.Receipt;

import dagger.Module;
import dagger.Provides;

@Module(library = true, overrides = true)
public class InputReceiptProducer {
  private final Receipt mReceipt;

  public InputReceiptProducer(Receipt receipt) {
    mReceipt = receipt;
  }

  @Provides
  @Product
  Receipt getReceipt() {
    return mReceipt;
  }
}