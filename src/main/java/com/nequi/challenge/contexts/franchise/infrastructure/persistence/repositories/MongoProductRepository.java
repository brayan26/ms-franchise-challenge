package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository extends ReactiveMongoRepository<ProductDocument, String> {
}
