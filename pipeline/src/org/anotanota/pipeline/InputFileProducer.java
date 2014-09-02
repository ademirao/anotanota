package org.anotanota.pipeline;

import java.io.File;

import org.anotanota.framework.pipeline.Product;

import dagger.Module;
import dagger.Provides;

@Module(library = true, overrides = true)
public class InputFileProducer {
  private final File mFile;

  public InputFileProducer(File file) {
    mFile = file;
  }

  @Provides
  @Product
  File getInputFile() {
    return mFile;
  }

}