package org.anotanota.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import dagger.ObjectGraph;

public abstract class Application extends android.app.Application {
  private final Stack<ObjectGraph> graphStack = new Stack<>();

  @Override
  public final void onCreate() {
    List<Object> modules = new ArrayList<>(getModules());
    modules.add(new ApplicationModule(this));
    graphStack.push(ObjectGraph.create(modules.toArray()));
  }

  public ObjectGraph getGraph() {
    return graphStack.peek();
  }

  public ObjectGraph openScope(Object... modules) {
    return graphStack.push(getGraph().plus(modules));
  }

  public ObjectGraph closeScope() {
    return graphStack.pop();
  }

  public abstract List<Object> getModules();
}