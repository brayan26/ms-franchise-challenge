package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IInventoryRepository {
   Mono<Inventory> addProduct(Inventory inventory);
   Mono<Void> updateStock(Inventory inventory);
   Flux<Inventory> getTopStockProductPerBranch(String franchiseId);
   Flux<Inventory> getInventoryByBranchOffice(String branchOfficeId);
   Mono<Void> deleteInventoryByProductIdAndBranchOfficeId(String branchOfficeId, String productId);
   Mono<Void> deleteProductWithInventory(String productId);
}
