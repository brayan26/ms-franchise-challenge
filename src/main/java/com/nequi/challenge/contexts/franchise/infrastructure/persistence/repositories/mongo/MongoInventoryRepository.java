package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.InventoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoInventoryRepository extends ReactiveMongoRepository<InventoryDocument, String> {
}
