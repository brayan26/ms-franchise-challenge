package com.nequi.challenge.contexts.franchise.application.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.inventory.*;
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
   private final InventoryEraserByProductIdAndBranchOfficeIdUseCase inventoryEraserByProductIdAndBranchOfficeIdUseCase;

   public Mono<Inventory> addProduct(Inventory inventory) {
      return this.inventoryCreatorUseCase.execute(inventory);
   }

   public Mono<Void> updateStock(Inventory inventory) {
      return this.inventoryStockUpdaterUseCase.execute(inventory);
   }

   public Mono<Void> deleteProductWithInventory(String branchOfficeId, String productId) {
      return this.inventoryEraserByProductIdAndBranchOfficeIdUseCase.execute(branchOfficeId, productId);
   }
   public Flux<Inventory> topStopProductPerBranchOfficeOfFranchise(String franchiseId) {
      return this.inventoryTopStockProductPerBranchOfficeUseCase.execute(franchiseId);
   }

   public Flux<Inventory> findInventoryByBranchOffice(String branchOfficeId) {
      return this.inventoryGetterByBranchOfficeUseCase.execute(branchOfficeId);
   }
}
