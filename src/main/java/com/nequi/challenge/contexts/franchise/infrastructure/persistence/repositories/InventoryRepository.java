package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import com.nequi.challenge.contexts.franchise.domain.repositories.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class InventoryRepository implements IInventoryRepository {
   @Override
   public Mono<Inventory> addProduct(Inventory inventory) {
      return null;
   }

   @Override
   public Mono<Void> updateStock(Inventory inventory) {
      return null;
   }

   @Override
   public Flux<Inventory> listByBranchOffice(String branchOfficeId) {
      return null;
   }
}
