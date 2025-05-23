package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoBranchOfficeRepository extends ReactiveMongoRepository<BranchOfficeDocument, String> {
   Flux<BranchOfficeDocument> findAllByFranchiseId(String franchiseId);
}
