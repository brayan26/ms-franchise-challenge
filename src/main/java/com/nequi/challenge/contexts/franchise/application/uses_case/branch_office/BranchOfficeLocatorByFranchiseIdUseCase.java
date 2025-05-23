package com.nequi.challenge.contexts.franchise.application.uses_case.branch_office;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.domain.repositories.IBranchOfficeRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BranchOfficeLocatorByFranchiseIdUseCase {
   private final IBranchOfficeRepository repository;

   public BranchOfficeLocatorByFranchiseIdUseCase(IBranchOfficeRepository repository) {
      this.repository = repository;
   }

   public Flux<BranchOffice> execute(String franchiseId) {
      return this.repository.findByFranchiseId(franchiseId);
   }
}
