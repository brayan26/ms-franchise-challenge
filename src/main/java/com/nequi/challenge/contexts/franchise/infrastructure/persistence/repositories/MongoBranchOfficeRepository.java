package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MongoBranchOfficeRepository extends ReactiveMongoRepository<BranchOfficeDocument, String> {
   Flux<BranchOfficeDocument> findAllByFranchiseId(String franchiseId);
}
