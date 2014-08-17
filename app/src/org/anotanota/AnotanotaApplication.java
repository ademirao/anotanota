package org.anotanota;

import java.util.Arrays;
import java.util.List;

import org.anotanota.framework.Application;
import org.anotanota.pipeline.AnotanotaPipelineModule;

public class AnotanotaApplication extends Application {
  @Override
  public List<Object> getModules() {
    return Arrays.<Object> asList(new AnotanotaModule(),
        new AnotanotaPipelineModule());
  }
}
