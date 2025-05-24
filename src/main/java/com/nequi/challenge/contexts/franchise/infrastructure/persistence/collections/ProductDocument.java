package com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "product")
public class ProductDocument {
   @Id
   private String id;
   private String name;
   private LocalDateTime created_at;
   private LocalDateTime updated_at;
}
