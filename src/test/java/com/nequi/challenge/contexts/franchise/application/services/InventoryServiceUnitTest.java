package com.nequi.challenge.contexts.franchise.infrastructure.services;

import com.nequi.challenge.contexts.franchise.application.services.InventoryService;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryGetterByBranchOfficeUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryStockUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryTopStockProductPerBranchOfficeUseCase;
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

   private InventoryService service;

   @BeforeEach
   void setup() {
      creator = mock(InventoryCreatorUseCase.class);
      updater = mock(InventoryStockUpdaterUseCase.class);
      topStockProductPerBranchOfficeUseCase = mock(InventoryTopStockProductPerBranchOfficeUseCase.class);
      inventoryGetterByBranchOfficeUseCase = mock(InventoryGetterByBranchOfficeUseCase.class);

      service = new InventoryService(creator, updater, topStockProductPerBranchOfficeUseCase, inventoryGetterByBranchOfficeUseCase);
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
}
