package com.nequi.challenge.contexts.franchise.application.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryGetterByBranchOfficeUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryStockUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.InventoryTopStockProductPerBranchOfficeUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryService {
   private final InventoryCreatorUseCase inventoryCreatorUseCase;
   private final InventoryStockUpdaterUseCase inventoryStockUpdaterUseCase;
   private final InventoryTopStockProductPerBranchOfficeUseCase inventoryTopStockProductPerBranchOfficeUseCase;
   private final InventoryGetterByBranchOfficeUseCase inventoryGetterByBranchOfficeUseCase;

   public Mono<Inventory> addProduct(Inventory inventory) {
      return this.inventoryCreatorUseCase.execute(inventory);
   }

   public Mono<Void> updateStock(Inventory inventory) {
      return this.inventoryStockUpdaterUseCase.execute(inventory);
   }

   public Flux<Inventory> topStopProductPerBranchOfficeOfFranchise(String franchiseId) {
      return this.inventoryTopStockProductPerBranchOfficeUseCase.execute(franchiseId);
   }

   public Flux<Inventory> findInventoryByBranchOffice(String branchOfficeId) {
      return this.inventoryGetterByBranchOfficeUseCase.execute(branchOfficeId);
   }
}
