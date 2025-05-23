package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryResponseDto(
      String id,
      String branchOfficeId,
      String branchOfficeName,
      String productId,
      String productName,
      BigDecimal productPrice,
      int stock,
      LocalDateTime created_at,
      LocalDateTime updated_at
) {
}
