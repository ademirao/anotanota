package org.anotanota.pipeline;

import org.anotanota.framework.pipeline.Pipeline;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AnotanotaPipelineModule {

  @Provides
  @Pipeline.Producers
  Object[] getProducers() {
    return new Object[] { new ReceiptProducer(), new ReceiptItemsProducer(),
        new ProductsProducer() };
  }
}
