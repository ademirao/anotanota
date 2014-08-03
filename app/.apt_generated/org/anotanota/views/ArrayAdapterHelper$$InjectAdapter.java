// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.views;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<ArrayAdapterHelper>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code ArrayAdapterHelper} and its
 * dependencies.
 *
 * Being a {@code Provider<ArrayAdapterHelper>} and handling creation and
 * preparation of object instances.
 */
public final class ArrayAdapterHelper$$InjectAdapter extends Binding<ArrayAdapterHelper>
    implements Provider<ArrayAdapterHelper> {
  private Binding<android.view.LayoutInflater> layoutInflater;
  private Binding<android.content.Context> context;

  public ArrayAdapterHelper$$InjectAdapter() {
    super("org.anotanota.views.ArrayAdapterHelper", "members/org.anotanota.views.ArrayAdapterHelper", NOT_SINGLETON, ArrayAdapterHelper.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    layoutInflater = (Binding<android.view.LayoutInflater>) linker.requestBinding("android.view.LayoutInflater", ArrayAdapterHelper.class, getClass().getClassLoader());
    context = (Binding<android.content.Context>) linker.requestBinding("android.content.Context", ArrayAdapterHelper.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    getBindings.add(layoutInflater);
    getBindings.add(context);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<ArrayAdapterHelper>}.
   */
  @Override
  public ArrayAdapterHelper get() {
    ArrayAdapterHelper result = new ArrayAdapterHelper(layoutInflater.get(), context.get());
    return result;
  }

}