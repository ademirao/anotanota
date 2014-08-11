package org.anotanota;

import javax.inject.Inject;

import org.anotanota.framework.Activity;
import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;
import org.anotanota.framework.drawer.NavigationDrawerViewController;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;

public class MainViewController implements UIViewController {
  private final LayoutInflater mInflater;
  private final ActionBar mActionBar;
  private final Activity mContext;
  private final Navigation mNavigation;
  private final NavigationDrawerViewController mNavigationDrawer;
  private ActionBarDrawerToggle mDrawerToggle;
  private final TesseractInstaller mInstaller;

  @Inject
  public MainViewController(LayoutInflater inflater,
    TesseractInstaller installer,
    ActionBar actionBar,
    Activity context,
    Navigation navigation,
    NavigationDrawerViewController navDrawer) {
    mInflater = inflater;
    mActionBar = actionBar;
    mContext = context;
    mNavigation = navigation;
    mNavigationDrawer = navDrawer;
    mInstaller = installer;
  }

  @Override
  public View loadView() {
    mInstaller.install();
    mNavigationDrawer.openItem(0);
    mActionBar.setDisplayHomeAsUpEnabled(true);
    mActionBar.setHomeButtonEnabled(true);
    return null;
  }
}
