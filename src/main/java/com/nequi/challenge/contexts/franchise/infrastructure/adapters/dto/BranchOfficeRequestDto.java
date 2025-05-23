package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BranchOfficeRequestDto(
      @NotNull
      @NotBlank
      String name,
      @NotNull
      @NotBlank
      String franchiseId
) {}
