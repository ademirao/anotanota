package org.anotanota;

import javax.inject.Qualifier;

public interface Anotanota {
  @Qualifier
  public @interface SelectedPaths {

  }

  public @interface Views {

    @Qualifier
    public @interface TabsFrame {

    }
  }

  @Qualifier
  public @interface TesseractAssetsPath {

  }

}
