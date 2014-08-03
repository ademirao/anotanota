package org.anotanota;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class FuturesUtil {
  private FuturesUtil() {
  }

  public static <V> V get(Future<V> f) {
    try {
      return f.get();
    } catch (ExecutionException ee) {
      throw new RuntimeException(ee);
    } catch (CancellationException ce) {
      throw new RuntimeException(ce);
    } catch (InterruptedException ie) {
      throw new RuntimeException(ie);
    }
  }
}
