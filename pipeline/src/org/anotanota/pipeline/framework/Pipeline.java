package org.anotanota.pipeline.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.dagger.scope.Scope;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import dagger.ObjectGraph;

public class Pipeline {
	private final Scope mScope;
	private final ListeningExecutorService mService;
	private final List<Object> modules;

	@Inject
	public Pipeline(ListeningExecutorService service, Scope scope, List<Object> modules) {
		mService = service;
		mScope = scope;
		this.modules = new ArrayList<Object>(modules);
	}

	public Pipeline addModule(Object module) {
		modules.add(module);
		return this;
	}

	public <T> ListenableFuture<T> produce(final Class<T> clazz) {
		System.out.println("Submitting...");

		return mService.submit(new Callable<T>() {
			@Override
			public T call() throws Exception {
				try {
					System.out.println("Creating with graph and scope...");
					Scope scope = mScope.newChild(modules.toArray());
					System.out.println("With scope...");
					ObjectGraph graph = scope.getGraph();
					System.out.println("Graph: " + graph);
					T t = graph.get(clazz);
					System.out.println("Done!");
					return t;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		});

	}

	public ListenableFuture<Void> produceInto(final Object into) {

		return mService.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				try {
					mScope.newChild(modules.toArray()).getGraph().inject(into);
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		});
	}
}
