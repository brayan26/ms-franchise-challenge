package com.nequi.challenge.contexts.franchise.infrastructure.persistence.repositories.mongo;

import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoBranchOfficeRepository extends ReactiveMongoRepository<BranchOfficeDocument, String> {
}
