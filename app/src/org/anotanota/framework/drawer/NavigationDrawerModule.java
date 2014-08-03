package org.anotanota.framework.drawer;

import org.anotanota.R;
import org.anotanota.framework.Activity;
import org.anotanota.framework.Navigation;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class NavigationDrawerModule {

  @Provides
  @NavigationDrawer.Toggle
  ActionBarDrawerToggle toggle(Activity activity,
                               @NavigationDrawer.Layout DrawerLayout drawerLayout,
                               final NavigationDrawerViewController navigationDrawer,
                               final Navigation navigation) {
    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,
        drawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
        R.string.drawer_close) {

      /** Called when a drawer has settled in a completely open state. */
      @Override
      public void onDrawerOpened(View drawerView) {
        navigation.navigateTo(navigationDrawer);
      }
    };

    drawerLayout.setDrawerListener(drawerToggle);
    return drawerToggle;
  }
}
