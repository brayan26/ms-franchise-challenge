package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class FranchiseMotherObject {
   public static Franchise random() {
      return new Franchise(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            LocalDateTime.now()
      );
   }
}
