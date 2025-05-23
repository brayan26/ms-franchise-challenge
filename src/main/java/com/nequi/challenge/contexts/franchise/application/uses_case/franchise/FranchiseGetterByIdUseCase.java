package com.nequi.challenge.contexts.franchise.application.uses_case.franchise;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.domain.repositories.IFranchiseRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FranchiseGetterByIdUseCase {
   private final IFranchiseRepository repository;

   public FranchiseGetterByIdUseCase(IFranchiseRepository repository) {
      this.repository = repository;
   }

   public Mono<Franchise> execute(String id) {
      return this.repository.findOne(id);
   }
}
