// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.framework.drawer;

import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class NavigationDrawerModule$$ModuleAdapter extends ModuleAdapter<NavigationDrawerModule> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public NavigationDrawerModule$$ModuleAdapter() {
    super(org.anotanota.framework.drawer.NavigationDrawerModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, false /*complete*/, true /*library*/);
  }

  @Override
  public NavigationDrawerModule newModule() {
    return new org.anotanota.framework.drawer.NavigationDrawerModule();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, NavigationDrawerModule module) {
    bindings.contributeProvidesBinding("@org.anotanota.framework.drawer.NavigationDrawer$Toggle()/android.support.v4.app.ActionBarDrawerToggle", new ToggleProvidesAdapter(module));
  }

  /**
   * A {@code Binding<android.support.v4.app.ActionBarDrawerToggle>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Owning the dependency links between {@code android.support.v4.app.ActionBarDrawerToggle} and its
   * dependencies.
   *
   * Being a {@code Provider<android.support.v4.app.ActionBarDrawerToggle>} and handling creation and
   * preparation of object instances.
   */
  public static final class ToggleProvidesAdapter extends ProvidesBinding<android.support.v4.app.ActionBarDrawerToggle>
      implements Provider<android.support.v4.app.ActionBarDrawerToggle> {
    private final NavigationDrawerModule module;
    private Binding<org.anotanota.framework.Activity> activity;
    private Binding<android.support.v4.widget.DrawerLayout> drawerLayout;
    private Binding<NavigationDrawerViewController> navigationDrawer;
    private Binding<org.anotanota.framework.Navigation> navigation;

    public ToggleProvidesAdapter(NavigationDrawerModule module) {
      super("@org.anotanota.framework.drawer.NavigationDrawer$Toggle()/android.support.v4.app.ActionBarDrawerToggle", NOT_SINGLETON, "org.anotanota.framework.drawer.NavigationDrawerModule", "toggle");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Used internally to link bindings/providers together at run time
     * according to their dependency graph.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void attach(Linker linker) {
      activity = (Binding<org.anotanota.framework.Activity>) linker.requestBinding("org.anotanota.framework.Activity", NavigationDrawerModule.class, getClass().getClassLoader());
      drawerLayout = (Binding<android.support.v4.widget.DrawerLayout>) linker.requestBinding("@org.anotanota.framework.drawer.NavigationDrawer$Layout()/android.support.v4.widget.DrawerLayout", NavigationDrawerModule.class, getClass().getClassLoader());
      navigationDrawer = (Binding<NavigationDrawerViewController>) linker.requestBinding("org.anotanota.framework.drawer.NavigationDrawerViewController", NavigationDrawerModule.class, getClass().getClassLoader());
      navigation = (Binding<org.anotanota.framework.Navigation>) linker.requestBinding("org.anotanota.framework.Navigation", NavigationDrawerModule.class, getClass().getClassLoader());
    }

    /**
     * Used internally obtain dependency information, such as for cyclical
     * graph detection.
     */
    @Override
    public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
      getBindings.add(activity);
      getBindings.add(drawerLayout);
      getBindings.add(navigationDrawer);
      getBindings.add(navigation);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.support.v4.app.ActionBarDrawerToggle>}.
     */
    @Override
    public android.support.v4.app.ActionBarDrawerToggle get() {
      return module.toggle(activity.get(), drawerLayout.get(), navigationDrawer.get(), navigation.get());
    }
  }
}