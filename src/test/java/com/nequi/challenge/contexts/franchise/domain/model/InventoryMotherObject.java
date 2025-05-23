package com.nequi.challenge.contexts.franchise.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryMotherObject {
   public static Inventory random() {
      return new Inventory(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            BigDecimal.TEN,
            10,
            LocalDateTime.now(),
            LocalDateTime.now()
      );
   }
}
