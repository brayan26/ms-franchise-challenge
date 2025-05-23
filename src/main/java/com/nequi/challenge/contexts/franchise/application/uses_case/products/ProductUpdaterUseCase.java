package com.nequi.challenge.contexts.franchise.application.uses_case.products;

import com.nequi.challenge.contexts.franchise.domain.model.Product;
import com.nequi.challenge.contexts.franchise.domain.repositories.IProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductUpdaterUseCase {
   private final IProductRepository repository;

   public ProductUpdaterUseCase(IProductRepository repository) {
      this.repository = repository;
   }

   public Mono<Product> execute(String id, Product product) {
      return this.repository.update(id, product);
   }
}
