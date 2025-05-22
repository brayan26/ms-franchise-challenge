package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Product;

public interface IProductRepository {
   Product create(Product product);
   Product update(Long id, Product product);
   void deleteProduct(Long id);
}
