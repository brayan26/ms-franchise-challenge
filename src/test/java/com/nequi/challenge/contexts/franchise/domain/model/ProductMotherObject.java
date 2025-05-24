package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductMotherObject {
   public static Product random() {
      return new Product(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            LocalDateTime.now()
      );
   }
}
