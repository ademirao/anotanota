// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.pipeline;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class ReceiptItemsProducer$Inputs$$ModuleAdapter extends ModuleAdapter<ReceiptItemsProducer.Inputs> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public ReceiptItemsProducer$Inputs$$ModuleAdapter() {
    super(org.anotanota.pipeline.ReceiptItemsProducer.Inputs.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, true /*library*/);
  }

  @Override
  public ReceiptItemsProducer.Inputs newModule() {
    return new org.anotanota.pipeline.ReceiptItemsProducer.Inputs();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, ReceiptItemsProducer.Inputs module) {
    bindings.contributeProvidesBinding("org.anotanota.model.Receipt", new GetReceiptProvidesAdapter(module));
    bindings.contributeProvidesBinding("org.anotanota.model.ReceiptItemsDataAccess", new GetDaProvidesAdapter(module));
  }

  /**
   * A {@code Binding<org.anotanota.model.Receipt>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<org.anotanota.model.Receipt>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetReceiptProvidesAdapter extends ProvidesBinding<org.anotanota.model.Receipt>
      implements Provider<org.anotanota.model.Receipt> {
    private final ReceiptItemsProducer.Inputs module;

    public GetReceiptProvidesAdapter(ReceiptItemsProducer.Inputs module) {
      super("org.anotanota.model.Receipt", NOT_SINGLETON, "org.anotanota.pipeline.ReceiptItemsProducer.Inputs", "getReceipt");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.anotanota.model.Receipt>}.
     */
    @Override
    public org.anotanota.model.Receipt get() {
      return module.getReceipt();
    }
  }

  /**
   * A {@code Binding<org.anotanota.model.ReceiptItemsDataAccess>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<org.anotanota.model.ReceiptItemsDataAccess>} and handling creation and
   * preparation of object instances.
   */
  public static final class GetDaProvidesAdapter extends ProvidesBinding<org.anotanota.model.ReceiptItemsDataAccess>
      implements Provider<org.anotanota.model.ReceiptItemsDataAccess> {
    private final ReceiptItemsProducer.Inputs module;

    public GetDaProvidesAdapter(ReceiptItemsProducer.Inputs module) {
      super("org.anotanota.model.ReceiptItemsDataAccess", NOT_SINGLETON, "org.anotanota.pipeline.ReceiptItemsProducer.Inputs", "getDa");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.anotanota.model.ReceiptItemsDataAccess>}.
     */
    @Override
    public org.anotanota.model.ReceiptItemsDataAccess get() {
      return module.getDa();
    }
  }
}