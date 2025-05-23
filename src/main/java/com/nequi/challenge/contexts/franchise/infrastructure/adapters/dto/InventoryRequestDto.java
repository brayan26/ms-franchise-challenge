package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InventoryRequestDto(
      @NotNull
      Long branchOfficeId,
      @NotNull
      Long productId,
      @NotNull
      int stock,
      @NotNull
      BigDecimal priceAmount
) {}
