package org.anotanota.framework;

import javax.inject.Qualifier;

public interface App {
  @Qualifier
  public @interface ActivityModules {
  }

  @Qualifier
  public @interface MainThread {
  }

  @Qualifier
  public @interface MainViewController {
  }

  @Qualifier
  public @interface ApplicationScope {

  }
}
