package com.nequi.challenge.contexts.franchise.application.uses_case.franchise;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.domain.repositories.IFranchiseRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FranchiseFinderUseCase {
   private final IFranchiseRepository repository;

   public FranchiseFinderUseCase(IFranchiseRepository repository) {
      this.repository = repository;
   }

   public Flux<Franchise> execute() {
      return this.repository.findAll();
   }
}
