package org.anotanota.framework.drawer;

import java.util.List;

import org.anotanota.R;
import org.anotanota.framework.Activity;
import org.anotanota.framework.ActivityModule;
import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;
import dagger.Module;
import dagger.Provides;

@Module(library = true, includes = { NavigationDrawerModule.Dependencies.class,
    ActivityModule.class }, overrides = true)
public class NavigationDrawerModule {

  @Module(library = true)
  public static class Dependencies {
    @Provides
    @NavigationDrawer.Layout
    DrawerLayout getLayout() {
      throw org.anotanota.framework.Application.getMissingBind(
          NavigationDrawer.Layout.class, DrawerLayout.class);
    }

    @Provides
    @NavigationDrawer.ViewControler
    UIViewController viewController() {
      throw org.anotanota.framework.Application.getMissingBind(
          NavigationDrawer.ViewControler.class, UIViewController.class);
    }

    @Provides
    @NavigationDrawer.Titles
    List<String> titles() {
      throw org.anotanota.framework.Application.getMissingBind(
          NavigationDrawer.Titles.class, List.class);
    }

    @Provides
    @NavigationDrawer.View
    ListView drawerView() {
      throw org.anotanota.framework.Application.getMissingBind(
          NavigationDrawer.View.class, ListView.class);
    }
  }

  @Provides
  @NavigationDrawer.Toggle
  ActionBarDrawerToggle toggle(Activity activity,
                               @NavigationDrawer.Layout DrawerLayout layout,
                               final NavigationDrawerViewController navigationDrawer,
                               final Navigation navigation) {
    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,
        layout, R.drawable.ic_drawer, R.string.drawer_open,
        R.string.drawer_close) {

      /** Called when a drawer has settled in a completely open state. */
      @Override
      public void onDrawerOpened(View drawerView) {
        navigation.navigateTo(navigationDrawer);
      }
    };

    layout.setDrawerListener(drawerToggle);
    return drawerToggle;
  }
}
