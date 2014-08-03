package org.anotanota;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.anotanota.framework.UIViewController;
import org.anotanota.framework.pager.Pager;
import org.anotanota.framework.pager.PagerAdapter;

import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.View;

/**
 * This is the main activity for the anotanota app.
 * 
 * @author ademirao
 */
public class DataSourceViewController implements UIViewController,
    ActionBar.TabListener {
  private final ActionBar mActionBar;
  private final String mTitle;
  private final ViewPager mViewsPager;
  private final PagerAdapter mPagerAdapter;
  private final List<String> mTabsTitles;

  @Inject
  public DataSourceViewController(@Named("DataAccessName") String title,
    PagerAdapter pagerAdapter,
    @Anotanota.Views.TabsFrame ViewPager viewsPager,
    @Pager.Titles List<String> tabsTitles,
    ActionBar actionBar) {
    mViewsPager = viewsPager;
    mPagerAdapter = pagerAdapter;
    mActionBar = actionBar;
    mTabsTitles = tabsTitles;
    mTitle = title;
  }

  @Override
  public View loadView() {
    mActionBar.setTitle(mTitle);
    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
        return null;
      }

      @Override
      protected void onPostExecute(Void result) {
        mActionBar.removeAllTabs();
        // Adding Tabs
        for (String title : mTabsTitles) {
          mActionBar.addTab(mActionBar.newTab().setText(title)
              .setTabListener(DataSourceViewController.this));
        }
        mViewsPager.setAdapter(mPagerAdapter);
      }
    }.execute();
    return null;
  }

  @Override
  public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
    mViewsPager.setCurrentItem(arg0.getPosition());
  }

  @Override
  public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
    // TODO Auto-generated method stub

  }
}
