package com.nequi.challenge.contexts.franchise.application.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseFinderUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseGetterByIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService {
   private final FranchiseCreatorUseCase franchiseCreatorUseCase;
   private final FranchiseUpdaterUseCase franchiseUpdaterUseCase;
   private final FranchiseFinderUseCase franchiseFinderUseCase;
   private final FranchiseGetterByIdUseCase franchiseGetterByIdUseCase;

   public Mono<Franchise> create(Franchise franchise) {
      return this.franchiseCreatorUseCase.execute(franchise);
   }

   public Mono<Franchise> update(String id, Franchise franchise) {
      return this.franchiseUpdaterUseCase.execute(id, franchise);
   }

   public Mono<Franchise> findById(String id) {
      return this.franchiseGetterByIdUseCase.execute(id);
   }

   public Flux<Franchise> findAll() {
      return this.franchiseFinderUseCase.execute();
   }
}
