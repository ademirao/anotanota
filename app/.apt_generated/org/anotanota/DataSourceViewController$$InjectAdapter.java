// Code generated by dagger-compiler.  Do not edit.
package org.anotanota;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<DataSourceViewController>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code DataSourceViewController} and its
 * dependencies.
 *
 * Being a {@code Provider<DataSourceViewController>} and handling creation and
 * preparation of object instances.
 */
public final class DataSourceViewController$$InjectAdapter extends Binding<DataSourceViewController>
    implements Provider<DataSourceViewController> {
  private Binding<String> title;
  private Binding<org.anotanota.framework.pager.PagerAdapter> pagerAdapter;
  private Binding<android.support.v4.view.ViewPager> viewsPager;
  private Binding<java.util.List<String>> tabsTitles;
  private Binding<android.support.v7.app.ActionBar> actionBar;

  public DataSourceViewController$$InjectAdapter() {
    super("org.anotanota.DataSourceViewController", "members/org.anotanota.DataSourceViewController", NOT_SINGLETON, DataSourceViewController.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    title = (Binding<String>) linker.requestBinding("@javax.inject.Named(value=DataAccessName)/java.lang.String", DataSourceViewController.class, getClass().getClassLoader());
    pagerAdapter = (Binding<org.anotanota.framework.pager.PagerAdapter>) linker.requestBinding("org.anotanota.framework.pager.PagerAdapter", DataSourceViewController.class, getClass().getClassLoader());
    viewsPager = (Binding<android.support.v4.view.ViewPager>) linker.requestBinding("@org.anotanota.Anotanota$Views$TabsFrame()/android.support.v4.view.ViewPager", DataSourceViewController.class, getClass().getClassLoader());
    tabsTitles = (Binding<java.util.List<String>>) linker.requestBinding("@org.anotanota.framework.pager.Pager$Titles()/java.util.List<java.lang.String>", DataSourceViewController.class, getClass().getClassLoader());
    actionBar = (Binding<android.support.v7.app.ActionBar>) linker.requestBinding("android.support.v7.app.ActionBar", DataSourceViewController.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    getBindings.add(title);
    getBindings.add(pagerAdapter);
    getBindings.add(viewsPager);
    getBindings.add(tabsTitles);
    getBindings.add(actionBar);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<DataSourceViewController>}.
   */
  @Override
  public DataSourceViewController get() {
    DataSourceViewController result = new DataSourceViewController(title.get(), pagerAdapter.get(), viewsPager.get(), tabsTitles.get(), actionBar.get());
    return result;
  }

}