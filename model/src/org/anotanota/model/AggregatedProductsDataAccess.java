package org.anotanota.model;

import java.util.List;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.ListenableFuture;

public interface AggregatedProductsDataAccess {
  Future<AggregatedProduct> getProduct(int id);

  ListenableFuture<List<AggregatedProduct>> listProducts();

  Future<Void> deleteProduct(int id);

  Future<Void> updateProduct(AggregatedProduct item);

  Future<Void> createProduct(AggregatedProduct item);
}
