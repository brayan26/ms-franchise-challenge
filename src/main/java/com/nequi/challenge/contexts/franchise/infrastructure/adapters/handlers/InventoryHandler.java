package com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.InventoryRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.InventoryResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.InventoryMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventoryHandler {
   private final InventoryMapper mapper;
   private final InventoryService service;

   public Mono<ServerResponse> addProduct(ServerRequest request) {
      return request.bodyToMono(InventoryRequestDto.class)
            .flatMap(dto -> this.service.addProduct(this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.CREATED)
                  .bodyValue(this.mapper.toResponseDto(response))
            );
   }

   public Mono<ServerResponse> updateStock(ServerRequest request) {
      return request.bodyToMono(InventoryRequestDto.class)
            .flatMap(dto -> this.service.updateStock(this.mapper.toDomain(dto)))
            .then(ServerResponse.ok().build());
   }

   public Mono<ServerResponse> findByBranchOfficeId(ServerRequest request) {
      String branchOfficeId = request.pathVariable("branchOfficeId");
      Flux<InventoryResponseDto> response = this.service.topStopProductPerBranchOfficeOfFranchise(branchOfficeId)
            .map(this.mapper::toResponseDto);
      return ServerResponse.ok().body(response, InventoryResponseDto.class);
   }

   public Mono<ServerResponse> delete(ServerRequest request) {
      String franchiseId = request.pathVariable("franchiseId");
      Flux<InventoryResponseDto> response = this.service.topStopProductPerBranchOfficeOfFranchise(franchiseId)
            .map(this.mapper::toResponseDto);
      return ServerResponse.ok().body(response, InventoryResponseDto.class);
   }
}
