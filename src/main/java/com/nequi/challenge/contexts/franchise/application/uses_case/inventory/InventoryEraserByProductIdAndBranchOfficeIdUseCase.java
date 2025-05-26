package com.nequi.challenge.contexts.franchise.application.uses_case.inventory;

import com.nequi.challenge.contexts.franchise.domain.repositories.IInventoryRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class InventoryEraserByProductIdAndBranchOfficeIdUseCase {
   private final IInventoryRepository repository;

   public InventoryEraserByProductIdAndBranchOfficeIdUseCase(IInventoryRepository repository) {
      this.repository = repository;
   }

   public Mono<Void> execute(String branchOfficeId, String productId) {
      return this.repository.deleteInventoryByProductIdAndBranchOfficeId(branchOfficeId, productId);
   }
}
