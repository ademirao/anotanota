// Code generated by dagger-compiler.  Do not edit.
package org.anotanota.pipeline.main;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<Main>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code Main} and its
 * dependencies.
 *
 * Being a {@code Provider<Main>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<Main>} and handling injection
 * of annotated fields.
 */
public final class Main$$InjectAdapter extends Binding<Main>
    implements Provider<Main>, MembersInjector<Main> {
  private Binding<org.anotanota.pipeline.framework.Pipeline> pipeline;

  public Main$$InjectAdapter() {
    super("org.anotanota.pipeline.main.Main", "members/org.anotanota.pipeline.main.Main", NOT_SINGLETON, Main.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    pipeline = (Binding<org.anotanota.pipeline.framework.Pipeline>) linker.requestBinding("org.anotanota.pipeline.framework.Pipeline", Main.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(pipeline);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<Main>}.
   */
  @Override
  public Main get() {
    Main result = new Main();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<Main>}.
   */
  @Override
  public void injectMembers(Main object) {
    object.pipeline = pipeline.get();
  }

}
