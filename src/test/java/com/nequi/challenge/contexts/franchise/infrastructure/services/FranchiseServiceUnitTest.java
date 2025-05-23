package com.nequi.challenge.contexts.franchise.infrastructure.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseFinderUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseGetterByIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.franchise.FranchiseUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.domain.model.FranchiseMotherObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class FranchiseServiceUnitTest {

   private FranchiseCreatorUseCase creator;
   private FranchiseUpdaterUseCase updater;
   private FranchiseGetterByIdUseCase getterById;
   private FranchiseFinderUseCase finder;

   private FranchiseService service;

   @BeforeEach
   void setup() {
      creator = mock(FranchiseCreatorUseCase.class);
      updater = mock(FranchiseUpdaterUseCase.class);
      getterById = mock(FranchiseGetterByIdUseCase.class);
      finder = mock(FranchiseFinderUseCase.class);
      service = new FranchiseService(creator, updater, finder, getterById);
   }

   @Test
   void shouldCreateFranchise() {
      Franchise franchise = FranchiseMotherObject.random();

      when(creator.execute(franchise)).thenReturn(Mono.just(franchise));

      StepVerifier.create(service.create(franchise))
            .expectNext(franchise)
            .verifyComplete();
   }

   @Test
   void shouldUpdateFranchise() {
      Franchise updated = FranchiseMotherObject.random();

      when(updater.execute(updated.id(), updated)).thenReturn(Mono.just(updated));

      StepVerifier.create(service.update(updated.id(), updated))
            .expectNext(updated)
            .verifyComplete();
   }

   @Test
   void shouldFindById() {
      Franchise franchise = FranchiseMotherObject.random();

      when(getterById.execute(franchise.id())).thenReturn(Mono.just(franchise));

      StepVerifier.create(service.findById(franchise.id()))
            .expectNext(franchise)
            .verifyComplete();
   }

   @Test
   void shouldFindAll() {
      Franchise f1 = FranchiseMotherObject.random();
      Franchise f2 = FranchiseMotherObject.random();

      when(finder.execute()).thenReturn(Flux.just(f1, f2));

      StepVerifier.create(service.findAll())
            .expectNext(f1)
            .expectNext(f2)
            .verifyComplete();
   }
}
