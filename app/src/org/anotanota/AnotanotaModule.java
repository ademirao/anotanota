package org.anotanota;

import org.anotanota.framework.App;
import org.anotanota.framework.ApplicationModule;
import org.anotanota.framework.drawer.NavigationDrawerModule;
import org.anotanota.sqlite.SQLiteModule;

import dagger.Module;
import dagger.Provides;

@Module(library = true, addsTo = ApplicationModule.class)
public class AnotanotaModule {
  @Provides
  @App.ActivityModules
  Object[] activityModules() {
    return new Object[] { new AnotanotaActivityModule(), new SQLiteModule(),
        new NavigationDrawerModule() };
  }
}
