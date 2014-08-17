package org.anotanota.framework;

import org.anotanota.framework.Scope.ScopeModule;
import org.anotanota.framework.drawer.NavigationDrawerModule;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import dagger.Module;
import dagger.Provides;

/**
 * THis module requires @App.ActivityModules/Object[] to be available.
 * 
 * @author ademirao
 */
@Module(includes = { NavigationDrawerModule.class, ScopeModule.class }, injects = Activity.class, library = true, addsTo = ApplicationModule.class, complete = false)
public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides
  ActionBarActivity actionBarActivity() {
    return mActivity;
  }

  @Provides
  Activity activity() {
    return mActivity;
  }

  @Provides
  Context context(ActionBarActivity activity) {
    return activity;
  }

  @Provides
  FragmentManager fragmentManager(ActionBarActivity activity) {
    return activity.getSupportFragmentManager();
  }

  @Provides
  ActionBar actionBar(ActionBarActivity activity) {
    return activity.getSupportActionBar();
  }

  @Provides
  AssetManager getAssetManager(ActionBarActivity activity) {
    return activity.getAssets();
  }

  @Provides
  LayoutInflater activityLayoutInflater(ActionBarActivity activity) {
    return activity.getLayoutInflater();
  }
}
