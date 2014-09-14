// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.pipeline;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<ReceiptItemsProducer.Result>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code ReceiptItemsProducer.Result} and its
 * dependencies.
 *
 * Being a {@code Provider<ReceiptItemsProducer.Result>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<ReceiptItemsProducer.Result>} and handling injection
 * of annotated fields.
 */
public final class ReceiptItemsProducer$Result$$InjectAdapter extends Binding<ReceiptItemsProducer.Result>
    implements Provider<ReceiptItemsProducer.Result>, MembersInjector<ReceiptItemsProducer.Result> {
  private Binding<java.util.List<org.anotanota.model.ReceiptItem>> result;

  public ReceiptItemsProducer$Result$$InjectAdapter() {
    super("org.anotanota.pipeline.ReceiptItemsProducer$Result", "members/org.anotanota.pipeline.ReceiptItemsProducer$Result", NOT_SINGLETON, ReceiptItemsProducer.Result.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    result = (Binding<java.util.List<org.anotanota.model.ReceiptItem>>) linker.requestBinding("java.util.List<org.anotanota.model.ReceiptItem>", ReceiptItemsProducer.Result.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(result);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<ReceiptItemsProducer.Result>}.
   */
  @Override
  public ReceiptItemsProducer.Result get() {
    ReceiptItemsProducer.Result result = new ReceiptItemsProducer.Result();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<ReceiptItemsProducer.Result>}.
   */
  @Override
  public void injectMembers(ReceiptItemsProducer.Result object) {
    object.result = result.get();
  }

}