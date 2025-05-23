package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.InventoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoInventoryRepository extends ReactiveMongoRepository<InventoryDocument, String> {
   Mono<Boolean> existsByBranchOfficeIdAndProductId(String branchOfficeId, String productId);
   Mono<InventoryDocument> findByBranchOfficeIdAndProductId(String branchOfficeId, String productId);
   Flux<InventoryDocument> findByBranchOfficeId(String branchOfficeId);
}
