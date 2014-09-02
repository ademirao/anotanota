// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.framework;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class ApplicationModule$Dependencies$$ModuleAdapter extends ModuleAdapter<ApplicationModule.Dependencies> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public ApplicationModule$Dependencies$$ModuleAdapter() {
    super(org.anotanota.framework.ApplicationModule.Dependencies.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, true /*library*/);
  }

  @Override
  public ApplicationModule.Dependencies newModule() {
    return new org.anotanota.framework.ApplicationModule.Dependencies();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, ApplicationModule.Dependencies module) {
    bindings.contributeProvidesBinding("@org.anotanota.framework.App$ActivityModules()/java.lang.Object[]", new GetModulesProvidesAdapter(module));
  }

  /**
   * A {@code Binding<java.lang.Object[]>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<java.lang.Object[]>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetModulesProvidesAdapter extends ProvidesBinding<Object[]>
      implements Provider<Object[]> {
    private final ApplicationModule.Dependencies module;

    public GetModulesProvidesAdapter(ApplicationModule.Dependencies module) {
      super("@org.anotanota.framework.App$ActivityModules()/java.lang.Object[]", NOT_SINGLETON, "org.anotanota.framework.ApplicationModule.Dependencies", "getModules");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<java.lang.Object[]>}.
     */
    @Override
    public Object[] get() {
      return module.getModules();
    }
  }
}