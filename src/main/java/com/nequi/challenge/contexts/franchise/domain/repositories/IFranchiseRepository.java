package com.nequi.challenge.contexts.franchise.domain.repositories;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IFranchiseRepository {
   Mono<Franchise> create(Franchise franchise);
   Mono<Franchise> update(String id, Franchise franchise);
   Flux<Franchise> findAll();
}
