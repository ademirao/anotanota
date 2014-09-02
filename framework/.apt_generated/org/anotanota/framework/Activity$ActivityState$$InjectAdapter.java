// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.framework;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<Activity.ActivityState>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code Activity.ActivityState} and its
 * dependencies.
 *
 * Being a {@code Provider<Activity.ActivityState>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<Activity.ActivityState>} and handling injection
 * of annotated fields.
 */
public final class Activity$ActivityState$$InjectAdapter extends Binding<Activity.ActivityState>
    implements Provider<Activity.ActivityState>, MembersInjector<Activity.ActivityState> {
  private Binding<android.support.v4.app.ActionBarDrawerToggle> mDrawerToggle;
  private Binding<UIViewController> mMainViewController;
  private Binding<Navigation> mNavigation;

  public Activity$ActivityState$$InjectAdapter() {
    super("org.anotanota.framework.Activity$ActivityState", "members/org.anotanota.framework.Activity$ActivityState", NOT_SINGLETON, Activity.ActivityState.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mDrawerToggle = (Binding<android.support.v4.app.ActionBarDrawerToggle>) linker.requestBinding("@org.anotanota.framework.drawer.NavigationDrawer$Toggle()/android.support.v4.app.ActionBarDrawerToggle", Activity.ActivityState.class, getClass().getClassLoader());
    mMainViewController = (Binding<UIViewController>) linker.requestBinding("@org.anotanota.framework.App$MainViewController()/org.anotanota.framework.UIViewController", Activity.ActivityState.class, getClass().getClassLoader());
    mNavigation = (Binding<Navigation>) linker.requestBinding("org.anotanota.framework.Navigation", Activity.ActivityState.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mDrawerToggle);
    injectMembersBindings.add(mMainViewController);
    injectMembersBindings.add(mNavigation);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<Activity.ActivityState>}.
   */
  @Override
  public Activity.ActivityState get() {
    Activity.ActivityState result = new Activity.ActivityState();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<Activity.ActivityState>}.
   */
  @Override
  public void injectMembers(Activity.ActivityState object) {
    object.mDrawerToggle = mDrawerToggle.get();
    object.mMainViewController = mMainViewController.get();
    object.mNavigation = mNavigation.get();
  }

}