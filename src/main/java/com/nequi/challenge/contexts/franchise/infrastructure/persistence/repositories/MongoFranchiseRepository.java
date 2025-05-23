package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoFranchiseRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}
