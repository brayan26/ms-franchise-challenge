package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoProductRepository extends ReactiveMongoRepository<ProductDocument, String> {
}
