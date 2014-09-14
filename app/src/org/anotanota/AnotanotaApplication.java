package org.anotanota;

import java.util.Arrays;
import java.util.List;

import org.anotanota.framework.Activity;
import org.anotanota.framework.ActivityModule;
import org.anotanota.framework.Application;

public class AnotanotaApplication extends Application {
  @Override
  public List<Object> getModules() {
    return Arrays.<Object> asList(new AnotanotaModule());
  }

  @Override
  public Object[] getModulesForActivity(Activity activity) {
    return new Object[] { new AnotanotaActivityModule(),
        new ActivityModule(activity) };
  }
}
