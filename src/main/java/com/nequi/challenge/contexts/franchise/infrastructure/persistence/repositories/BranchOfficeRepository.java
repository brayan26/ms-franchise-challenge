package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.domain.repositories.IBranchOfficeRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.BranchOfficeMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo.MongoBranchOfficeRepository;
import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class BranchOfficeRepository implements IBranchOfficeRepository {
   private final MongoBranchOfficeRepository repository;
   private final BranchOfficeMapper mapper;

   @Override
   public Mono<BranchOffice> create(BranchOffice branchOffice) {
      BranchOfficeDocument document = this.mapper.toEntity(branchOffice);
      document.setCreated_at(LocalDateTime.now());
      return this.repository.save(document).map(this.mapper::toDomain);
   }

   @Override
   public Mono<BranchOffice> update(String id, BranchOffice branchOffice) {
      return findById(id)
            .map(this.mapper::toEntity)
            .map(document -> {
               if (branchOffice.name() != null) document.setName(branchOffice.name());
               if (branchOffice.franchiseId() != null) document.setFranchiseId(branchOffice.franchiseId());
               document.setUpdated_at(LocalDateTime.now());
               return document;
            })
            .flatMap(this.repository::save)
            .map(this.mapper::toDomain);
   }

   public Mono<BranchOffice> findById(String id) {
      return this.repository.findById(id).switchIfEmpty(
                  Mono.error(new GenericNotFoundException(
                        String.format("Branch office with id %s not found in the database", id),
                        BuildErrorUtil.create(
                              ErrorMessages.BRANCHOFFICE_NOT_FOUND_CODE,
                              ErrorMessages.BRANCHOFFICE_NOT_FOUND_DESCRIPTION
                        )))
            )
            .map(this.mapper::toDomain);
   }

   @Override
   public Flux<BranchOffice> findByFranchiseId(String franchiseId) {
      return this.repository.findAllByFranchiseId(franchiseId).map(this.mapper::toDomain);
   }
}
