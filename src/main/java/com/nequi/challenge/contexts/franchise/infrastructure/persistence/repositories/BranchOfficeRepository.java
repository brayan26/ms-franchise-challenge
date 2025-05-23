package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.domain.repositories.IBranchOfficeRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.BranchOfficeMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo.MongoBranchOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BranchOfficeRepository implements IBranchOfficeRepository {
   private final MongoBranchOfficeRepository repository;
   private final BranchOfficeMapper mapper;

   @Override
   public Mono<BranchOffice> create(BranchOffice branchOffice) {
      BranchOfficeDocument document = this.mapper.toEntity(branchOffice);
      return this.repository.save(document).map(this.mapper::toDomain);
   }

   @Override
   public Mono<BranchOffice> update(String id, BranchOffice branchOffice) {
      return null;
   }

   @Override
   public Flux<BranchOffice> findByFranchiseId(String franchiseId) {
      return null;
   }
}
