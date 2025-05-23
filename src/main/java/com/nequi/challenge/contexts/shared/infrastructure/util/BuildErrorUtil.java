package com.nequi.challenge.contexts.shared.infrastructure.util;

import com.nequi.challenge.contexts.shared.domain.constants.ErrorMessages;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericBadRequestException;
import com.nequi.challenge.contexts.shared.domain.exceptions.GenericNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuildErrorUtil {
   public static Map<String, String> create(String code, String description) {
      Map<String, String> error = new LinkedHashMap<>();
      error.put(code, description);
      return error;
   }

   public static Mono<ServerResponse> buildErrorResponse(Throwable ex) {
      if (ex instanceof GenericNotFoundException e) {
         return ServerResponse
               .status(HttpStatus.NOT_FOUND)
               .bodyValue(e.getError());
      }

      if (ex instanceof GenericBadRequestException e) {
         return ServerResponse
               .status(HttpStatus.BAD_REQUEST)
               .bodyValue(e.getError());
      }

      return ServerResponse
            .status(HttpStatus.BAD_REQUEST)
            .bodyValue(Map.of("error", ErrorMessages.UNKNOW_ERROR_DESCRIPTION));
   }
}
