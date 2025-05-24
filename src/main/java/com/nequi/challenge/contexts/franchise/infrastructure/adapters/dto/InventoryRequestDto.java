package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InventoryRequestDto(
      @NotNull
      @NotBlank
      String branchOfficeId,
      @NotNull
      @NotBlank
      String productId,
      @NotNull
      int stock,
      BigDecimal priceAmount
) {}
