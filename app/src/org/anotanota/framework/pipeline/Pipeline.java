package org.anotanota.framework.pipeline;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

import org.anotanota.framework.ActivityModule;
import org.anotanota.framework.Scope;
import org.anotanota.pipeline.Result;

import dagger.Module;
import dagger.Provides;

public class Pipeline {
  private final Scope mScope;
  private final ThreadPoolExecutor mThreadPool;
  private final Object[] mProducers;

  public @interface InputFile {

  }

  public @interface Producers {

  }

  public @interface Executor {

  }

  @Inject
  public Pipeline(@Executor ThreadPoolExecutor threadPool,
    Scope scope,
    @Producers Object[] producers) {
    mThreadPool = threadPool;
    mScope = scope;
    mProducers = producers;
  }

  @Module(library = true, addsTo = ActivityModule.class, injects = PipelineResult.class, complete = false)
  public class PipelineModule {
    private final File mReceipt;

    private PipelineModule(File file) {
      mReceipt = file;
    }

    @Provides
    @InputFile
    File getReceipt() {
      return mReceipt;
    }
  }

  static class PipelineResult {
    @Product
    @Inject
    Result result;
  }

  public void startPipelineFor(final File file) {
    mThreadPool.execute(new Runnable() {

      @Override
      public void run() {
        List<Object> producers = new ArrayList<Object>(Arrays
            .asList(mProducers));
        producers.add(new PipelineModule(file));
        mScope.newChild(producers.toArray()).getGraph()
            .inject(new PipelineResult());
      }
    });
  }
}
