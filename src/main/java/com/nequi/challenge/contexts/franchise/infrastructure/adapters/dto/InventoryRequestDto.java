package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InventoryRequestDto(
      @NotNull
      String branchOfficeId,
      @NotNull
      String productId,
      @NotNull
      int stock,
      BigDecimal priceAmount
) {}
