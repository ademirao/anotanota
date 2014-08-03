package org.anotanota.framework.pager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.anotanota.framework.Navigation;
import org.anotanota.framework.UIViewController;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

@Singleton
public class PagerAdapter extends FragmentStatePagerAdapter {
  private final List<String> mTabs;
  private final Provider<UIViewController> mViewController;
  private int mCurrentTab;

  @Inject
  public PagerAdapter(FragmentManager fm,
    @Pager.Titles List<String> pagerTitles,
    @Pager.Tabs Provider<UIViewController> viewController) {
    super(fm);
    mTabs = pagerTitles;
    mViewController = viewController;
  }

  public int getCurrentTab() {
    return mCurrentTab;
  }

  @Override
  public Fragment getItem(int i) {
    mCurrentTab = i;
    Fragment frag = Navigation.fragmentFor(mViewController.get());
    return frag;
  }

  @Override
  public int getCount() {
    return mTabs.size();
  }
}