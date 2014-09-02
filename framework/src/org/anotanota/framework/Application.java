package org.anotanota.framework;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.dagger.scope.Scope;

public abstract class Application extends android.app.Application {
  private Scope mAppScope = null;

  @Override
  public final void onCreate() {
    List<Object> modules = new ArrayList<Object>(getModules());
    ApplicationModule appModule = new ApplicationModule(this);
    modules.add(appModule);
    mAppScope = Scope.newRootScope(modules.toArray());
  }

  public Scope getAppScope() {
    return mAppScope;
  }

  public abstract List<Object> getModules();

  public static RuntimeException getMissingBind(Class<? extends Annotation> clazz,
                                                Class<?> type) {
    return new RuntimeException("Missing binding override " + clazz
        + " for type " + type);
  }
}