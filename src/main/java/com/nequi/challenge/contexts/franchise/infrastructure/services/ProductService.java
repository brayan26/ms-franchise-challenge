package com.nequi.challenge.contexts.franchise.infrastructure.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductEraserUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductLocatorByIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
   private final ProductCreatorUseCase productCreatorUseCase;
   private final ProductUpdaterUseCase productUpdaterUseCase;
   private final ProductEraserUseCase productEraserUseCase;
   private final ProductLocatorByIdUseCase productLocatorByIdUseCase;

   public Mono<Product> create(Product product) {
      return this.productCreatorUseCase.execute(product);
   }

   public Mono<Product> update(String id, Product product) {
      return this.productUpdaterUseCase.execute(id, product);
   }

   public Mono<Product> findById(String productId) {
      return this.productLocatorByIdUseCase.execute(productId);
   }

   public Mono<Void> delete(String productId) {
      return this.productEraserUseCase.execute(productId);
   }
}
