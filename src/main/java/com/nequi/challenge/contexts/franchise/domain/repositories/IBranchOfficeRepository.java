package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBranchOfficeRepository {
   Mono<BranchOffice> create(BranchOffice branchOffice);
   Mono<BranchOffice> update(String id, BranchOffice branchOffice);
   Flux<BranchOffice> findByFranchiseId(String franchiseId);
}
