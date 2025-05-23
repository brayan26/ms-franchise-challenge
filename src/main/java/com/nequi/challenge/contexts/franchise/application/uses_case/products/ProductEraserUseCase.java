package com.nequi.challenge.contexts.franchise.application.uses_case.products;

import com.nequi.challenge.contexts.franchise.domain.repositories.IProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductEraserUseCase {
   private final IProductRepository repository;

   public ProductEraserUseCase(IProductRepository repository) {
      this.repository = repository;
   }

   public Mono<Void> execute(String id) {
      return this.repository.deleteProduct(id);
   }
}
