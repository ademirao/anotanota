package org.anotanota.framework;

import javax.inject.Inject;

import org.anotanota.R;
import org.anotanota.framework.drawer.NavigationDrawer;
import org.anotanota.framework.drawer.NavigationDrawerModule;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * This is the main activity for the anotanota app.
 * 
 * @author ademirao
 */
public class Activity extends ActionBarActivity {
  Application mApplication;

  public static class ActivityModules {
    @Inject
    @App.ActivityModules
    Object[] mActivityModules;
  }

  @Inject
  @App.MainViewController
  UIViewController mMainViewController;

  @Inject
  @NavigationDrawer.Toggle
  ActionBarDrawerToggle mDrawerToggle;

  @Inject
  Navigation mNavigation;

  @Override
  public void onBackPressed() {
    mNavigation.back();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    mDrawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mApplication.closeScope();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mApplication = ((Application) getApplication());
    setContentView(R.layout.activity_main);
    ActivityModules modules = mApplication.getGraph()
        .get(ActivityModules.class);
    Object modulesArray[] = new Object[modules.mActivityModules.length + 2];
    modulesArray[0] = new ActivityModule(this);
    modulesArray[1] = new NavigationDrawerModule();
    System.arraycopy(modules.mActivityModules, 0, modulesArray, 2,
        modules.mActivityModules.length);
    mApplication.openScope(modulesArray).inject(this);
    mNavigation.navigateTo(mMainViewController);
  }
}
