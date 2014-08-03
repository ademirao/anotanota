package org.anotanota.framework.drawer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
@Singleton
public class NavigationDrawerViewController implements UIViewController {
  private final Navigation mNavigation;
  private final ListView mDrawerView;
  private final ActionBar mActionBar;
  private final DrawerLayout mDrawerLayout;
  private final Provider<UIViewController> mViewController;
  private final List<String> mTitles;
  private int mCurrentPosition = -1;

  @Inject
  public NavigationDrawerViewController(ActionBar actionBar,
    Navigation navigation,
    @NavigationDrawer.ViewControler Provider<UIViewController> viewController,
    @NavigationDrawer.Titles List<String> titles,
    @NavigationDrawer.View ListView drawerView,
    @NavigationDrawer.Layout DrawerLayout drawerLayout) {
    mNavigation = navigation;
    mDrawerView = drawerView;
    mActionBar = actionBar;
    mDrawerLayout = drawerLayout;
    mViewController = viewController;
    mTitles = titles;
  }

  public int getCurrentPosition() {
    return mCurrentPosition;
  }

  public void openItem(int position) {
    mCurrentPosition = position;
    mNavigation.navigateTo(mViewController.get());
    mDrawerLayout.closeDrawers();
  }

  @Override
  public View loadView() {
    mDrawerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent,
                              View view,
                              int position,
                              long id) {
        openItem(position);
      }
    });

    ArrayAdapter<String> barItems = new ArrayAdapter<String>(
        mActionBar.getThemedContext(), android.R.layout.simple_list_item_1,
        android.R.id.text1, mTitles);
    mDrawerView.setAdapter(barItems);

    return null;
  }
}
