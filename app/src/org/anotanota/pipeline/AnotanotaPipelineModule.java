package org.anotanota.pipeline;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AnotanotaPipelineModule {
  @Provides
  @Named("FullPipelineProducers")
  Object[] getProducers() {
    return new Object[] { new ReceiptProducer(), new ReceiptItemsProducer(),
        new ProductsProducer() };
  }

}
