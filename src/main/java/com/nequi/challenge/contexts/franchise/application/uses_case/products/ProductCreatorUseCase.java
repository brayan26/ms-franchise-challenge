package com.nequi.challenge.contexts.franchise.application.uses_case.products;

import com.nequi.challenge.contexts.franchise.domain.model.Product;
import com.nequi.challenge.contexts.franchise.domain.repositories.IProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductCreatorUseCase {
   private final IProductRepository repository;

   public ProductCreatorUseCase(IProductRepository repository) {
      this.repository = repository;
   }

   public Mono<Product> execute(Product product) {
      return this.repository.create(product);
   }
}
