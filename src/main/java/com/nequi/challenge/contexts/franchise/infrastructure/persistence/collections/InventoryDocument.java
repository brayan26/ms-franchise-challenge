package com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "inventory")
@CompoundIndexes({
      @CompoundIndex(name = "branch_stock_idx", def = "{'branchOfficeId': 1, 'stock': -1}"),
      @CompoundIndex(name = "branch_product_idx", def = "{'branchOfficeId': 1, 'productId': 1}")
})
public class InventoryDocument {
   @Id
   private String id;
   private String branchOfficeId;
   private String productId;
   private int stock;
   private BigDecimal price;
   private LocalDateTime created_at;
   private LocalDateTime updated_at;
}
