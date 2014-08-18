package org.anotanota.framework;

import org.anotanota.framework.App.MainViewController;
import org.anotanota.framework.Scope.ScopeModule;
import org.anotanota.framework.drawer.NavigationDrawer;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.ActionBarDrawerToggle;
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
@Module(library = true, injects = { Activity.ActivityState.class }, includes = {
    ScopeModule.class, ActivityModule.Dependencies.class }, overrides = true, addsTo = ApplicationModule.class)
public class ActivityModule {

  @Module(library = true)
  public static class Dependencies {
    @Provides
    @MainViewController
    UIViewController getMainViewController() {
      throw Application.getMissingBind(MainViewController.class,
          UIViewController.class);
    }

    @Provides
    @NavigationDrawer.Toggle
    ActionBarDrawerToggle getToggle() {
      throw Application.getMissingBind(NavigationDrawer.Toggle.class,
          ActionBarDrawerToggle.class);
    }
  }

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
  Context getContext(ActionBarActivity activity) {
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
