package com.nequi.challenge.contexts.franchise.infrastructure.services;

import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeCreatorUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeLocatorByFranchiseIdUseCase;
import com.nequi.challenge.contexts.franchise.application.uses_case.branch_office.BranchOfficeUpdaterUseCase;
import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchOfficeService {
   private final BranchOfficeCreatorUseCase branchOfficeCreatorUseCase;
   private final BranchOfficeUpdaterUseCase branchOfficeUpdaterUseCase;
   private final BranchOfficeLocatorByFranchiseIdUseCase branchOfficeLocatorByFranchiseIdUseCase;

   public Mono<BranchOffice> create(BranchOffice domain) {
      return this.branchOfficeCreatorUseCase.execute(domain);
   }

   public Mono<BranchOffice> update(String id, BranchOffice domain) {
      return this.branchOfficeUpdaterUseCase.execute(id, domain);
   }

   public Flux<BranchOffice> findByFranchiseId(String franchiseId) {
      return this.branchOfficeLocatorByFranchiseIdUseCase.execute(franchiseId);
   }
}
