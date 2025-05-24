package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class BranchOfficeMotherObject {
   public static BranchOffice random() {
      return new BranchOffice(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            LocalDateTime.now()
      );
   }
}
