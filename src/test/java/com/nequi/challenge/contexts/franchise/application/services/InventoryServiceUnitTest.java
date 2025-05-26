package com.nequi.challenge.contexts.franchise.application.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.*;
import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import com.nequi.challenge.contexts.franchise.domain.model.InventoryMotherObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class InventoryServiceUnitTest {

   private InventoryCreatorUseCase creator;
   private InventoryStockUpdaterUseCase updater;
   private InventoryTopStockProductPerBranchOfficeUseCase topStockProductPerBranchOfficeUseCase;
   private InventoryGetterByBranchOfficeUseCase inventoryGetterByBranchOfficeUseCase;
   private InventoryEraserByProductIdAndBranchOfficeIdUseCase inventoryEraserByProductIdAndBranchOfficeIdUseCase;

   private InventoryService service;

   @BeforeEach
   void setup() {
      creator = mock(InventoryCreatorUseCase.class);
      updater = mock(InventoryStockUpdaterUseCase.class);
      topStockProductPerBranchOfficeUseCase = mock(InventoryTopStockProductPerBranchOfficeUseCase.class);
      inventoryGetterByBranchOfficeUseCase = mock(InventoryGetterByBranchOfficeUseCase.class);
      inventoryEraserByProductIdAndBranchOfficeIdUseCase = mock(InventoryEraserByProductIdAndBranchOfficeIdUseCase.class);

      service = new InventoryService(creator, updater, topStockProductPerBranchOfficeUseCase,
            inventoryGetterByBranchOfficeUseCase, inventoryEraserByProductIdAndBranchOfficeIdUseCase);
   }

   @Test
   void shouldCreateInventoryWhenProductAndBranchExist() {
      Inventory inventory = InventoryMotherObject.random();

      when(creator.execute(inventory)).thenReturn(Mono.just(inventory));

      StepVerifier.create(service.addProduct(inventory))
            .expectNext(inventory)
            .verifyComplete();

      verify(creator).execute(inventory);
   }

   @Test
   void shouldUpdateInventory() {
      Inventory inventory = InventoryMotherObject.random();

      when(updater.execute(inventory)).thenReturn(Mono.empty());

      StepVerifier.create(service.updateStock(inventory))
            .verifyComplete();

      verify(updater).execute(inventory);
   }

   @Test
   void shouldReturnATopStockProductPerBranchOffice() {
      Inventory f1 = InventoryMotherObject.random();
      Inventory f2 = InventoryMotherObject.random();
      String franchiseId = "123";

      when(topStockProductPerBranchOfficeUseCase.execute(franchiseId)).thenReturn(Flux.just(f1, f2));

      StepVerifier.create(service.topStopProductPerBranchOfficeOfFranchise(franchiseId))
            .expectNext(f1)
            .expectNext(f2)
            .verifyComplete();
   }

   @Test
   void shouldReturnInventoryOfBranchOffice() {
      Inventory f1 = InventoryMotherObject.random();
      Inventory f2 = InventoryMotherObject.random();
      String branchOfficeId = "123";

      when(inventoryGetterByBranchOfficeUseCase.execute(branchOfficeId)).thenReturn(Flux.just(f1, f2));

      StepVerifier.create(service.findInventoryByBranchOffice(branchOfficeId))
            .expectNext(f1)
            .expectNext(f2)
            .verifyComplete();

      verify(inventoryGetterByBranchOfficeUseCase).execute(branchOfficeId);
   }

   @Test
   void shouldEraserInventoryByProductId() {
      Inventory inventory = InventoryMotherObject.random();

      when(inventoryEraserByProductIdAndBranchOfficeIdUseCase.execute(inventory.branchOfficeId(), inventory.productId())).thenReturn(Mono.empty());

      StepVerifier.create(service.deleteProductWithInventory(inventory.branchOfficeId(), inventory.productId()))
            .verifyComplete();

      verify(inventoryEraserByProductIdAndBranchOfficeIdUseCase).execute(inventory.branchOfficeId(), inventory.productId());
   }
}
