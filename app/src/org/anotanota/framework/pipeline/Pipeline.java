package org.anotanota.framework.pipeline;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.anotanota.framework.Scope;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

public class Pipeline {
  private final Scope mScope;
  private final ListeningExecutorService mService;

  public @interface Executor {
  }

  @Inject
  public Pipeline(@Executor ListeningExecutorService service, Scope scope) {
    mService = service;
    mScope = scope;
  }

  public <T> ListenableFuture<T> produce(final Class<T> clazz,
                                         final List<Object> modules) {
    return mService.submit(new Callable<T>() {
      @Override
      public T call() throws Exception {
        return mScope.newChild(modules.toArray()).getGraph().get(clazz);
      }
    });
  }

  public ListenableFuture<Void> produceInto(final Class<Void> clazz,
                                            final Object into,
                                            final List<Object> modules) {
    return mService.submit(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        mScope.newChild(modules.toArray()).getGraph().inject(into);
        return null;
      }
    });
  }
}
