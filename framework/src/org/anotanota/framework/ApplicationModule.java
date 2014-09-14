package org.anotanota.framework;

import java.util.concurrent.Executor;

import org.anotanota.framework.App.ActivityModules;
import org.dagger.scope.Scope;

import android.os.Handler;
import android.os.Looper;
import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationModule {
  private final Application mApplication;

  public ApplicationModule(Application app) {
    mApplication = app;
  }

  @Provides
  Application application() {
    return mApplication;
  }

  @Provides
  @App.MainThread
  Handler mainLooper() {
    return new Handler(Looper.getMainLooper());
  }

  @Provides
  Executor executor() {
    return new Executor() {
      @Override
      public void execute(Runnable command) {
        new Handler().post(command);
      }
    };
  }

  @Provides
  @App.MainThread
  Executor mainthreadExecutor(@App.MainThread final Handler handler) {
    return new Executor() {
      @Override
      public void execute(Runnable command) {
        handler.post(command);
      }
    };
  }

  @Provides
  @App.ApplicationScope
  Scope scope(Application app) {
    return app.getAppScope();
  }
}
