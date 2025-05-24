package com.nequi.challenge.contexts.franchise.application.uses_case.branch_office;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.domain.repositories.IBranchOfficeRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BranchOfficeUpdaterUseCase {
   private final IBranchOfficeRepository repository;

   public BranchOfficeUpdaterUseCase(IBranchOfficeRepository repository) {
      this.repository = repository;
   }

   public Mono<BranchOffice> execute(String id, BranchOffice toUpdate) {
      return this.repository.update(id, toUpdate);
   }
}
