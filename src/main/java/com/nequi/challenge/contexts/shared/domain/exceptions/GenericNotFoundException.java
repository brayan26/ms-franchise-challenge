package com.nequi.challenge.contexts.shared.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericNotFoundException extends RuntimeException {
   private final Object error;

   public GenericNotFoundException(String message, Object error) {
      super(message);
      this.error = error;
   }
}
