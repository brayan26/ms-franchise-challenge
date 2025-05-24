package com.nequi.challenge.contexts.franchise.application.services;

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
   private final FranchiseService franchiseService;
   private final BranchOfficeCreatorUseCase branchOfficeCreatorUseCase;
   private final BranchOfficeUpdaterUseCase branchOfficeUpdaterUseCase;
   private final BranchOfficeLocatorByFranchiseIdUseCase branchOfficeLocatorByFranchiseIdUseCase;

   public Mono<BranchOffice> create(BranchOffice domain) {
      // validate franchise
      this.franchiseService.findById(domain.franchiseId());
      // save branch office
      return this.branchOfficeCreatorUseCase.execute(domain);
   }

   public Mono<BranchOffice> update(String id, BranchOffice domain) {
      // validate franchise
      this.franchiseService.findById(domain.franchiseId());
      // save branch office
      return this.branchOfficeUpdaterUseCase.execute(id, domain);
   }

   public Flux<BranchOffice> findByFranchiseId(String franchiseId) {
      return this.branchOfficeLocatorByFranchiseIdUseCase.execute(franchiseId);
   }
}
