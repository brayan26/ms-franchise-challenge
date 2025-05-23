package com.nequi.challenge.contexts.franchise.infrastructure.adapters.handlers;

import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.ProductRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.mappers.ProductMapper;
import com.nequi.challenge.contexts.franchise.infrastructure.services.ProductService;
import com.nequi.challenge.contexts.shared.infrastructure.util.BuildErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {
   private final ProductMapper mapper;
   private final ProductService service;

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(ProductRequestDto.class)
            .flatMap(dto -> this.service.create(this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.CREATED)
                  .bodyValue(this.mapper.toResponseDto(response))
            )
            .onErrorResume(BuildErrorUtil::buildErrorResponse);
   }

   public Mono<ServerResponse> update(ServerRequest request) {
      String id = request.pathVariable("id");
      return request.bodyToMono(ProductRequestDto.class)
            .flatMap(dto -> this.service.update(id, this.mapper.toDomain(dto)))
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.OK)
                  .bodyValue(this.mapper.toResponseDto(response))
            )
            .onErrorResume(BuildErrorUtil::buildErrorResponse);
   }

   public Mono<ServerResponse> findById(ServerRequest request) {
      String id = request.pathVariable("id");
      return this.service.findById(id)
            .flatMap(response -> ServerResponse
                  .status(HttpStatus.OK)
                  .bodyValue(this.mapper.toResponseDto(response))
            )
            .onErrorResume(BuildErrorUtil::buildErrorResponse);
   }

   public Mono<ServerResponse> delete(ServerRequest request) {
      String id = request.pathVariable("id");
      return this.service.delete(id).then(ServerResponse.noContent().build());
   }
}
