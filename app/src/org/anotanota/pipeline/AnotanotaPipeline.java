package org.anotanota.pipeline;

import javax.inject.Qualifier;

public interface AnotanotaPipeline {
  @Qualifier
  public @interface FullPipeline {

  }

  @Qualifier
  public @interface ItemsFromString {

  }
}
