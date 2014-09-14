package org.anotanota.pipeline;

import java.util.Arrays;

import org.anotanota.pipeline.framework.Pipeline;
import org.dagger.scope.Scope;
import org.dagger.scope.ScopeModule;

import com.google.common.util.concurrent.ListeningExecutorService;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = { ScopeModule.class }, complete=false)
public class AnotanotaPipelineModule {

  @Provides
  @AnotanotaPipeline.FullPipeline
  Pipeline getProducers(Scope scope, ListeningExecutorService service) {
	  return new Pipeline(service, scope, Arrays.asList(new ReceiptProducer(), new ReceiptItemsProducer(),
		        new ProductsProducer()));
  }
}
