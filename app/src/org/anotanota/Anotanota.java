package org.anotanota;

import javax.inject.Qualifier;

public interface Anotanota {
  @Qualifier
  public @interface OCRThread {

  }

  public @interface Views {

    @Qualifier
    public @interface TabsFrame {

    }
  }
}
