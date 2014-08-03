package org.anotanota.framework.drawer;

import javax.inject.Qualifier;

public interface NavigationDrawer {
  @Qualifier
  public @interface Toggle {
  }

  @Qualifier
  public @interface Titles {
  }

  @Qualifier
  public @interface ViewControler {
  }

  @Qualifier
  public @interface View {
  }

  @Qualifier
  public @interface Layout {
  }

}
