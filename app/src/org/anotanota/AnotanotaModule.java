package org.anotanota;

import org.anotanota.framework.App;
import org.anotanota.framework.ApplicationModule;
import org.anotanota.framework.drawer.NavigationDrawerModule;
import org.anotanota.pipeline.AnotanotaPipelineModule;
import org.anotanota.sqlite.SQLiteModule;

import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = { AnotanotaPipelineModule.class }, addsTo = ApplicationModule.class, overrides = true)
public class AnotanotaModule {
  @Provides
  @App.ActivityModules
  Object[] activityModules() {
    return new Object[] { new AnotanotaActivityModule(), new SQLiteModule(),
        new NavigationDrawerModule() };
  }
}
