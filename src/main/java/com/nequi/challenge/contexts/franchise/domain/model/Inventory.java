package com.nequi.challenge.contexts.franchise.domain.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Inventory(
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

   @Builder
   public static Inventory create(String id, String branchOfficeId, String branchOfficeName, String productId,
                                  String productName, BigDecimal productPrice, int stock,
                                  LocalDateTime created_at, LocalDateTime updated_at) {
      return new Inventory(id, branchOfficeId, branchOfficeName, productId, productName, productPrice, stock, created_at, updated_at);
   }
}

