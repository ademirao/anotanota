// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.pipeline.main;

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
public final class Main$MainModule$$ModuleAdapter extends ModuleAdapter<Main.MainModule> {
  private static final String[] INJECTS = { "members/org.anotanota.pipeline.main.Main", };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { org.anotanota.pipeline.AnotanotaPipelineModule.class, org.dagger.scope.ScopeModule.class, };

  public Main$MainModule$$ModuleAdapter() {
    super(org.anotanota.pipeline.main.Main.MainModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, false /*library*/);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, Main.MainModule module) {
    bindings.contributeProvidesBinding("org.anotanota.pipeline.OCR", new GetOcrProvidesAdapter(module));
    bindings.contributeProvidesBinding("org.apache.commons.cli.Options", new GetOptionsProvidesAdapter(module));
    bindings.contributeProvidesBinding("org.apache.commons.cli.CommandLine", new GetCmProvidesAdapter(module));
    bindings.contributeProvidesBinding("com.google.common.util.concurrent.ListeningExecutorService", new GetServiceProvidesAdapter(module));
    bindings.contributeProvidesBinding("org.anotanota.pipeline.framework.Pipeline", new GetPipelineProvidesAdapter(module));
  }

  /**
   * A {@code Binding<org.anotanota.pipeline.OCR>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<org.anotanota.pipeline.OCR>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetOcrProvidesAdapter extends ProvidesBinding<org.anotanota.pipeline.OCR>
      implements Provider<org.anotanota.pipeline.OCR> {
    private final Main.MainModule module;

    public GetOcrProvidesAdapter(Main.MainModule module) {
      super("org.anotanota.pipeline.OCR", NOT_SINGLETON, "org.anotanota.pipeline.main.Main.MainModule", "getOcr");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.anotanota.pipeline.OCR>}.
     */
    @Override
    public org.anotanota.pipeline.OCR get() {
      return module.getOcr();
    }
  }

  /**
   * A {@code Binding<org.apache.commons.cli.Options>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<org.apache.commons.cli.Options>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetOptionsProvidesAdapter extends ProvidesBinding<org.apache.commons.cli.Options>
      implements Provider<org.apache.commons.cli.Options> {
    private final Main.MainModule module;

    public GetOptionsProvidesAdapter(Main.MainModule module) {
      super("org.apache.commons.cli.Options", NOT_SINGLETON, "org.anotanota.pipeline.main.Main.MainModule", "getOptions");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.apache.commons.cli.Options>}.
     */
    @Override
    public org.apache.commons.cli.Options get() {
      return module.getOptions();
    }
  }

  /**
   * A {@code Binding<org.apache.commons.cli.CommandLine>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Owning the dependency links between {@code org.apache.commons.cli.CommandLine} and its
   * dependencies.
   *
   * Being a {@code Provider<org.apache.commons.cli.CommandLine>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetCmProvidesAdapter extends ProvidesBinding<org.apache.commons.cli.CommandLine>
      implements Provider<org.apache.commons.cli.CommandLine> {
    private final Main.MainModule module;
    private Binding<org.apache.commons.cli.Options> options;

    public GetCmProvidesAdapter(Main.MainModule module) {
      super("org.apache.commons.cli.CommandLine", NOT_SINGLETON, "org.anotanota.pipeline.main.Main.MainModule", "getCm");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Used internally to link bindings/providers together at run time
     * according to their dependency graph.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void attach(Linker linker) {
      options = (Binding<org.apache.commons.cli.Options>) linker.requestBinding("org.apache.commons.cli.Options", Main.MainModule.class, getClass().getClassLoader());
    }

    /**
     * Used internally obtain dependency information, such as for cyclical
     * graph detection.
     */
    @Override
    public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
      getBindings.add(options);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.apache.commons.cli.CommandLine>}.
     */
    @Override
    public org.apache.commons.cli.CommandLine get() {
      return module.getCm(options.get());
    }
  }

  /**
   * A {@code Binding<com.google.common.util.concurrent.ListeningExecutorService>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<com.google.common.util.concurrent.ListeningExecutorService>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetServiceProvidesAdapter extends ProvidesBinding<com.google.common.util.concurrent.ListeningExecutorService>
      implements Provider<com.google.common.util.concurrent.ListeningExecutorService> {
    private final Main.MainModule module;

    public GetServiceProvidesAdapter(Main.MainModule module) {
      super("com.google.common.util.concurrent.ListeningExecutorService", NOT_SINGLETON, "org.anotanota.pipeline.main.Main.MainModule", "getService");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<com.google.common.util.concurrent.ListeningExecutorService>}.
     */
    @Override
    public com.google.common.util.concurrent.ListeningExecutorService get() {
      return module.getService();
    }
  }

  /**
   * A {@code Binding<org.anotanota.pipeline.framework.Pipeline>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Owning the dependency links between {@code org.anotanota.pipeline.framework.Pipeline} and its
   * dependencies.
   *
   * Being a {@code Provider<org.anotanota.pipeline.framework.Pipeline>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetPipelineProvidesAdapter extends ProvidesBinding<org.anotanota.pipeline.framework.Pipeline>
      implements Provider<org.anotanota.pipeline.framework.Pipeline> {
    private final Main.MainModule module;
    private Binding<org.anotanota.pipeline.framework.Pipeline> pipeline;
    private Binding<org.apache.commons.cli.CommandLine> cml;

    public GetPipelineProvidesAdapter(Main.MainModule module) {
      super("org.anotanota.pipeline.framework.Pipeline", NOT_SINGLETON, "org.anotanota.pipeline.main.Main.MainModule", "getPipeline");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Used internally to link bindings/providers together at run time
     * according to their dependency graph.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void attach(Linker linker) {
      pipeline = (Binding<org.anotanota.pipeline.framework.Pipeline>) linker.requestBinding("@org.anotanota.pipeline.AnotanotaPipeline$FullPipeline()/org.anotanota.pipeline.framework.Pipeline", Main.MainModule.class, getClass().getClassLoader());
      cml = (Binding<org.apache.commons.cli.CommandLine>) linker.requestBinding("org.apache.commons.cli.CommandLine", Main.MainModule.class, getClass().getClassLoader());
    }

    /**
     * Used internally obtain dependency information, such as for cyclical
     * graph detection.
     */
    @Override
    public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
      getBindings.add(pipeline);
      getBindings.add(cml);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.anotanota.pipeline.framework.Pipeline>}.
     */
    @Override
    public org.anotanota.pipeline.framework.Pipeline get() {
      return module.getPipeline(pipeline.get(), cml.get());
    }
  }
}
