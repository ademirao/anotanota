package org.anotanota.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

public class Scope {
  private final ObjectGraph mObjectGraph;

  private Scope(ObjectGraph og) {
    mObjectGraph = og;
  }

  public ObjectGraph getGraph() {
    return mObjectGraph;
  }

  public Scope newChild(Object... modules) {
    ScopeModule module = new ScopeModule();
    List<Object> modulesList = new ArrayList<Object>(Arrays.asList(modules));
    modulesList.add(module);
    System.out.println("Creating graph");
    ObjectGraph graph = mObjectGraph.plus(modulesList.toArray());
    System.out.println("Graph created!");
    Scope s = new Scope(graph);
    module.setScope(s);
    return s;
  }

  public static Scope newRootScope(Object... modules) {
    ScopeModule module = new ScopeModule();
    List<Object> modulesList = new ArrayList<Object>(Arrays.asList(modules));
    modulesList.add(module);
    ObjectGraph graph = ObjectGraph.create(modulesList.toArray());
    Scope s = new Scope(graph);
    module.setScope(s);
    return s;
  }

  @Module(library = true)
  public static class ScopeModule {
    private Scope mScope;

    private void setScope(Scope scope) {
      mScope = scope;
    }

    @Provides
    Scope getScope() {
      return mScope;
    }
  }
}
