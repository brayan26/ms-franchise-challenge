package com.nequi.challenge.contexts.franchise.infrastructure.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeLocatorByFranchiseIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.domain.model.BranchOfficeMotherObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

public class BranchOfficeServiceUnitTest {

   private FranchiseService franchiseService;
   private BranchOfficeCreatorUseCase creatorUseCase;
   private BranchOfficeUpdaterUseCase updaterUseCase;
   private BranchOfficeLocatorByFranchiseIdUseCase locatorUseCase;

   private BranchOfficeService service;

   @BeforeEach
   void setup() {
      franchiseService = mock(FranchiseService.class);
      creatorUseCase = mock(BranchOfficeCreatorUseCase.class);
      updaterUseCase = mock(BranchOfficeUpdaterUseCase.class);
      locatorUseCase = mock(BranchOfficeLocatorByFranchiseIdUseCase.class);

      service = new BranchOfficeService(franchiseService, creatorUseCase, updaterUseCase, locatorUseCase);
   }

   @Test
   void shouldCreateBranchOffice() {
      BranchOffice input = BranchOfficeMotherObject.random();

      when(franchiseService.findById(input.franchiseId())).thenReturn(Mono.just(mock()));
      when(creatorUseCase.execute(input)).thenReturn(Mono.just(input));

      StepVerifier.create(service.create(input))
            .expectNext(input)
            .verifyComplete();

      verify(franchiseService, times(1)).findById(input.franchiseId());
      verify(creatorUseCase, times(1)).execute(input);
   }

   @Test
   void shouldReturnBranchesByFranchiseId() {
      BranchOffice office = BranchOfficeMotherObject.random();

      when(locatorUseCase.execute(office.franchiseId())).thenReturn(Flux.just(office));

      StepVerifier.create(service.findByFranchiseId(office.franchiseId()))
            .expectNext(office)
            .verifyComplete();
   }
}