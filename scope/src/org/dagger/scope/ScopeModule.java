package org.dagger.scope;

import dagger.Module;
import dagger.Provides;

@Module(library = true) 
public class ScopeModule {
    private Scope mScope;

    void setScope(Scope scope) {
      mScope = scope;
    }

    @Provides
    Scope getScope() {
      return mScope;
    }
  }