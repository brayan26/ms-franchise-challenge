package com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.DtoValidator;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.BranchOfficeMapper;
import com.nequi.challenge.contexts.franchise.application.services.BranchOfficeService;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchOfficeHandler {
   private final DtoValidator validator;
   private final BranchOfficeMapper mapper;
   private final BranchOfficeService service;

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(BranchOfficeRequestDto.class)
            .doOnNext(validator::validate)
            .flatMap(dto -> this.service.create(this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.CREATED)
                  .bodyValue(this.mapper.toResponseDto(response))
            )
            .onErrorResume(BuildErrorUtil::buildErrorResponse);
   }

   public Mono<ServerResponse> update(ServerRequest request) {
      String id = request.pathVariable("id");
      return request.bodyToMono(BranchOfficeRequestDto.class)
            .doOnNext(validator::validate)
            .flatMap(dto -> this.service.update(id, this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.OK)
                  .bodyValue(this.mapper.toResponseDto(response))
            )
            .onErrorResume(BuildErrorUtil::buildErrorResponse);
   }

   public Mono<ServerResponse> findByFranchiseId(ServerRequest request) {
      String franchiseId = request.pathVariable("franchiseId");
      Flux<BranchOfficeResponseDto> response = this.service.findByFranchiseId(franchiseId)
            .map(mapper::toResponseDto);

      return ServerResponse
            .ok()
            .body(response, BranchOfficeResponseDto.class);

   }
}
