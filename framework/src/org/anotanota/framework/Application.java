package org.anotanota.framework;

import java.util.ArrayList;
import java.util.List;

import org.dagger.scope.Scope;

public abstract class Application extends android.app.Application {
  private Scope mAppScope = null;

  @Override
  public final void onCreate() {
    List<Object> modules = new ArrayList<Object>(getModules());
    modules.add(new ApplicationModule(this));
    mAppScope = Scope.newRootScope(modules.toArray());
  }

  public Scope getAppScope() {
    return mAppScope;
  }

  public abstract List<Object> getModules();

  public abstract Object[] getModulesForActivity(Activity activity);
}