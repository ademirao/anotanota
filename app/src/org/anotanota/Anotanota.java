package org.anotanota;

import javax.inject.Qualifier;

public interface Anotanota {
  @Qualifier
  public @interface SelectedPaths {

  }

  @Qualifier
  public @interface TesseractAssetsPath {

  }

  @Qualifier
  public @interface TesseractConfig {

  }

  @Qualifier
  public @interface TesseractInstallPath {

  }

  public @interface Views {

    @Qualifier
    public @interface TabsFrame {

    }
  }

}
