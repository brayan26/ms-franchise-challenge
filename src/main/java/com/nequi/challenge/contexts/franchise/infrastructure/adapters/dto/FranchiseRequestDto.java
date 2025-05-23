package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FranchiseRequestDto(
      @NotNull
      @NotBlank
      String name
) {}
