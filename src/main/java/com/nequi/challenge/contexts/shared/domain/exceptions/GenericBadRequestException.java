package com.nequi.challenge.contexts.shared.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericBadRequestException extends RuntimeException {
   private final Object error;

   public GenericBadRequestException(String message, Object error) {
      super(message);
      this.error = error;
   }
}
