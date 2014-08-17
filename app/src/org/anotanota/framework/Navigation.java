package org.anotanota.framework;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.anotanota.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@Singleton
public class Navigation {
  private static final class NavigationFragment extends Fragment {
    private final UIViewController mViewController;

    public NavigationFragment(UIViewController controller) {
      mViewController = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
      return mViewController.loadView();
    }
  }

  private final FragmentManager mFragmentManager;

  @Inject
  public Navigation(FragmentManager fragmentManager) {
    mFragmentManager = fragmentManager;
  }

  public void open(UIViewController viewController) {
    NavigationFragment fragment = new NavigationFragment(viewController);
    mFragmentManager.beginTransaction().replace(R.id.content, fragment)
        .commit();
  }

  public void navigateTo(UIViewController viewController) {
    NavigationFragment fragment = new NavigationFragment(viewController);
    mFragmentManager.beginTransaction()
        .addToBackStack(Long.toHexString(viewController.hashCode()))
        .replace(R.id.content, fragment).commit();
  }

  public static Fragment fragmentFor(UIViewController controller) {
    return new NavigationFragment(controller);
  }

  public void back() {
    mFragmentManager.popBackStack();
  }
}
