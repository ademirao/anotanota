// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.sqlite;

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
public final class SQLiteModule$$ModuleAdapter extends ModuleAdapter<SQLiteModule> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public SQLiteModule$$ModuleAdapter() {
    super(org.anotanota.sqlite.SQLiteModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, false /*complete*/, true /*library*/);
  }

  @Override
  public SQLiteModule newModule() {
    return new org.anotanota.sqlite.SQLiteModule();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, SQLiteModule module) {
    bindings.contributeProvidesBinding("java.util.List<org.anotanota.sqlite.SQLiteModule$SQLiteDataAccessModule>", new DataSourcesProvidesAdapter(module));
  }

  /**
   * A {@code Binding<java.util.List<org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule>>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Owning the dependency links between {@code java.util.List<org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule>} and its
   * dependencies.
   *
   * Being a {@code Provider<java.util.List<org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule>>} and handling creation and
   * preparation of object instances.
   */
  public static final class DataSourcesProvidesAdapter extends ProvidesBinding<java.util.List<SQLiteModule.SQLiteDataAccessModule>>
      implements Provider<java.util.List<SQLiteModule.SQLiteDataAccessModule>> {
    private final SQLiteModule module;
    private Binding<android.content.Context> context;

    public DataSourcesProvidesAdapter(SQLiteModule module) {
      super("java.util.List<org.anotanota.sqlite.SQLiteModule$SQLiteDataAccessModule>", NOT_SINGLETON, "org.anotanota.sqlite.SQLiteModule", "dataSources");
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
      context = (Binding<android.content.Context>) linker.requestBinding("android.content.Context", SQLiteModule.class, getClass().getClassLoader());
    }

    /**
     * Used internally obtain dependency information, such as for cyclical
     * graph detection.
     */
    @Override
    public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
      getBindings.add(context);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<java.util.List<org.anotanota.sqlite.SQLiteModule.SQLiteDataAccessModule>>}.
     */
    @Override
    public java.util.List<SQLiteModule.SQLiteDataAccessModule> get() {
      return module.dataSources(context.get());
    }
  }
}