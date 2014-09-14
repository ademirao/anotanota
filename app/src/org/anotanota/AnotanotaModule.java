package org.anotanota;

import com.googlecode.tesseract.android.TessBaseAPI;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AnotanotaModule {

  @Provides
  TessBaseAPI getTessBaseApi() {
    return new TessBaseAPI();
  }
}
