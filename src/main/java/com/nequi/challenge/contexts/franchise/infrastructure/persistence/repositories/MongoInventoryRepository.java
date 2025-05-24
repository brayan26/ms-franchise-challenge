package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.InventoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MongoInventoryRepository extends ReactiveMongoRepository<InventoryDocument, String> {
   Mono<Boolean> existsByBranchOfficeIdAndProductId(String branchOfficeId, String productId);
   Mono<InventoryDocument> findByBranchOfficeIdAndProductId(String branchOfficeId, String productId);
   Flux<InventoryDocument> findByBranchOfficeId(String branchOfficeId);
}
