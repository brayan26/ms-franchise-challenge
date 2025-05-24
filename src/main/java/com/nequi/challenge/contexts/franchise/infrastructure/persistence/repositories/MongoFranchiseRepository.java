package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoFranchiseRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}
