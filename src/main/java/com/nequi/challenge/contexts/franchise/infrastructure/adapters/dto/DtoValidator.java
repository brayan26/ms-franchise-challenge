package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import com.nequi.challenge.contexts.shared.domain.exceptions.GenericBadRequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoValidator {

   private final Validator validator;

   public DtoValidator() {
      try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
         this.validator = factory.getValidator();
      }
   }

   public <T> void validate(T object) {
      Set<ConstraintViolation<T>> violations = validator.validate(object);
      if (!violations.isEmpty()) {
         String message = violations.stream()
               .map(ConstraintViolation::getMessage)
               .collect(Collectors.joining(", "));
         throw new GenericBadRequestException(String.format("Error in dto Validation <%s>", message), Map.of("errors", message));
      }
   }
}

