package com.nequi.challenge.contexts.franchise.application.uses_case.inventory;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import com.nequi.challenge.contexts.franchise.domain.repositories.IInventoryRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class InventoryTopStockProductPerBranchOfficeUseCase {
   private final IInventoryRepository repository;

   public InventoryTopStockProductPerBranchOfficeUseCase(IInventoryRepository repository) {
      this.repository = repository;
   }

   public Flux<Inventory> execute(String franchiseId) {
      return this.repository.getTopStockProductPerBranch(franchiseId);
   }
}
