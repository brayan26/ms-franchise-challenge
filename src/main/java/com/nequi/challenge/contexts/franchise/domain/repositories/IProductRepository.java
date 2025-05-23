package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Product;
import reactor.core.publisher.Mono;

public interface IProductRepository {
   Mono<Product> create(Product product);
   Mono<Product> update(String id, Product product);
   Mono<Void> deleteProduct(String id);
}
