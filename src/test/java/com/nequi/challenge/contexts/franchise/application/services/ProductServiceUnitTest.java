package com.nequi.challenge.contexts.franchise.application.services;

import com.nequi.challenge.contexts.franchise.application.services.ProductService;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductEraserUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductLocatorByIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.products.ProductUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.Product;
import com.nequi.challenge.contexts.franchise.domain.model.ProductMotherObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ProductServiceUnitTest {

   private ProductCreatorUseCase creator;
   private ProductUpdaterUseCase updater;
   private ProductEraserUseCase eraserUseCase;
   private ProductLocatorByIdUseCase productLocatorByIdUseCase;
   private ProductService service;

   @BeforeEach
   void setup() {
      creator = mock(ProductCreatorUseCase.class);
      updater = mock(ProductUpdaterUseCase.class);
      eraserUseCase = mock(ProductEraserUseCase.class);
      productLocatorByIdUseCase = mock(ProductLocatorByIdUseCase.class);
      service = new ProductService(creator, updater, eraserUseCase, productLocatorByIdUseCase);
   }

   @Test
   void shouldCreateProduct() {
      Product product = ProductMotherObject.random();

      when(creator.execute(product)).thenReturn(Mono.just(product));

      StepVerifier.create(service.create(product))
            .expectNext(product)
            .verifyComplete();
   }

   @Test
   void shouldUpdateProduct() {
      Product updated = ProductMotherObject.random();

      when(updater.execute(updated.id(), updated)).thenReturn(Mono.just(updated));

      StepVerifier.create(service.update(updated.id(), updated))
            .expectNext(updated)
            .verifyComplete();
   }

   @Test
   void shouldFindById() {
      Product product = ProductMotherObject.random();

      when(productLocatorByIdUseCase.execute(product.id())).thenReturn(Mono.just(product));

      StepVerifier.create(service.findById(product.id()))
            .expectNext(product)
            .verifyComplete();
   }


}
