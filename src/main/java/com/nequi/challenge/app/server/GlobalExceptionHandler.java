package com.nequi.challenge.app.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericBadRequestException;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
   private final ObjectMapper objectMapper;

   public GlobalExceptionHandler(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
   }

   @Override
   public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
      HttpStatus status = HttpStatus.BAD_REQUEST;
      log.error("{}", ex.getLocalizedMessage());
      Object errorDetail = Map.of("error", ErrorMessages.UNKNOW_ERROR_DESCRIPTION);

      if (ex instanceof GenericNotFoundException e) {
         errorDetail = e.getError();
         status = HttpStatus.NOT_FOUND;
      }

      if (ex instanceof GenericBadRequestException e) {
         errorDetail = e.getError();
      }

      exchange.getResponse().setStatusCode(status);
      exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
      DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();

      try {
         byte[] bytes = objectMapper.writeValueAsBytes(errorDetail);
         return exchange.getResponse().writeWith(Mono.just(bufferFactory.wrap(bytes)));
      } catch (Exception e) {
         return exchange.getResponse().setComplete();
      }
   }
}
