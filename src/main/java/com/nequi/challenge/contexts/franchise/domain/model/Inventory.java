package com.nequi.challenge.contexts.franchise.domain.model;

import lombok.Builder;

import java.math.BigDecimal;

public record Inventory(
      Long branchOfficeId,
      String branchOfficeName,
      Long productId,
      String productName,
      BigDecimal productPrice,
      int stock
) {

   @Builder
   public static Inventory create(Long branchOfficeId, String branchOfficeName, Long productId,
                                  String productName, BigDecimal productPrice, int stock) {
      return new Inventory(branchOfficeId, branchOfficeName, productId, productName, productPrice, stock);
   }
}

