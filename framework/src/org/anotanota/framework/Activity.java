package org.anotanota.framework;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.anotanota.framework.R;
import org.anotanota.framework.drawer.NavigationDrawer;
import org.dagger.scope.Scope;

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

  ActivityState mState;

  public static class ActivityState {
    @Inject
    @App.MainViewController
    UIViewController mMainViewController;

    @Inject
    @NavigationDrawer.Toggle
    ActionBarDrawerToggle mDrawerToggle;

    @Inject
    Navigation mNavigation;
  }

  @Override
  public void onBackPressed() {
    mState.mNavigation.back();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    mState.mDrawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mState.mDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (mState.mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mApplication = ((Application) getApplication());
    setContentView(R.layout.activity_main);
    Scope appScope = mApplication.getAppScope();
    mState = appScope
    		.newChild(mApplication.getModulesForActivity(this)).getGraph()
        .get(ActivityState.class);
    mState.mNavigation.navigateTo(mState.mMainViewController);
  }
}
