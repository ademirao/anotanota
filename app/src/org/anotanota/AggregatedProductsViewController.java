package org.anotanota;

import javax.inject.Inject;

import org.anotanota.framework.UIViewController;

import android.support.v7.app.ActionBar;
import android.view.View;

public class AggregatedProductsViewController implements UIViewController {

  private final ActionBar mActionBar;

  @Inject
  public AggregatedProductsViewController(ActionBar actionBar) {
    mActionBar = actionBar;
  }

  @Override
  public View loadView() {
    // TODO Auto-generated method stub
    return null;
  }

}
