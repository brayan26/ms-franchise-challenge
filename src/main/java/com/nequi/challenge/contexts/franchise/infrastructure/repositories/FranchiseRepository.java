package com.nequi.challenge.contexts.franchise.infrastructure.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.domain.repositories.IFranchiseRepository;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.FranchiseMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.FranchiseDocument;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.MongoFranchiseRepository;
import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FranchiseRepository implements IFranchiseRepository {
   private final MongoFranchiseRepository repository;
   private final FranchiseMapper mapper;

   @Override
   public Mono<Franchise> create(Franchise franchise) {
      FranchiseDocument document = this.mapper.toEntity(franchise);
      document.setCreated_at(LocalDateTime.now());
      return this.repository.save(document).map(this.mapper::toDomain);
   }

   @Override
   public Mono<Franchise> update(String id, Franchise franchise) {
      return this.findOne(id)
            .map(this.mapper::toEntity)
            .map(document -> {
               if (franchise.name() != null) {
                  document.setName(franchise.name());
                  document.setUpdated_at(LocalDateTime.now());
               }
               return document;
            }).flatMap(this.repository::save)
            .map(this.mapper::toDomain);
   }

   @Override
   public Mono<Franchise> findOne(String id) {
      return this.repository.findById(id).switchIfEmpty(
            Mono.error(new GenericNotFoundException(
                  String.format("Franchise id %s not found in the database", id),
                  BuildErrorUtil.create(ErrorMessages.FRANCHISE_NOT_FOUND_CODE, ErrorMessages.FRANCHISE_NOT_FOUND_DESCRIPTION)
            ))
      ).map(this.mapper::toDomain);
   }

   @Override
   public Flux<Franchise> findAll() {
      return this.repository.findAll().map(this.mapper::toDomain);
   }
}
