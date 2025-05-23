package com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.FranchiseMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.services.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {
   private final FranchiseMapper mapper;
   private final FranchiseService service;

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(FranchiseRequestDto.class)
            .flatMap(dto -> this.service.create(this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.CREATED)
                  .bodyValue(this.mapper.toResponseDto(response))
            );
   }

   public Mono<ServerResponse> update(ServerRequest request) {
      String id = request.pathVariable("id");
      return request.bodyToMono(FranchiseRequestDto.class)
            .flatMap(dto -> this.service.update(id, this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.OK)
                  .bodyValue(this.mapper.toResponseDto(response))
            );
   }

   public Mono<ServerResponse> findById(ServerRequest request) {
      String franchiseId = request.pathVariable("id");
      return this.service.findById(franchiseId)
            .flatMap(response -> ServerResponse
            .status(HttpStatus.OK)
            .bodyValue(this.mapper.toResponseDto(response))
      );
   }

   public Mono<ServerResponse> findAll(ServerRequest request) {
      return ServerResponse
            .ok()
            .body(this.service.findAll().map(this.mapper::toResponseDto), FranchiseResponseDto.class);
   }
}
