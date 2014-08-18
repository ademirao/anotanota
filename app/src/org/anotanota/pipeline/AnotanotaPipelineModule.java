package org.anotanota.pipeline;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AnotanotaPipelineModule {
  @Provides
  @AnotanotaPipeline.FullPipeline
  Object[] getProducers() {
    return new Object[] { new ReceiptProducer(), new ReceiptItemsProducer(),
        new ProductsProducer() };
  }

  @Provides
  @AnotanotaPipeline.ItemsFromString
  Object[] getProducersItemsFromString() {
    return new Object[] { new ReceiptItemsProducer() };
  }

}
