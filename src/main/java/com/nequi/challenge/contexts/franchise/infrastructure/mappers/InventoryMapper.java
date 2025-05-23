package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.Inventory;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.InventoryRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.InventoryResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.InventoryDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
   @Mapping(source = "branchOfficeId", target = "branchOfficeId")
   @Mapping(source = "productId", target = "productId")
   @Mapping(source = "priceAmount", target = "productPrice")
   @Mapping(source = "stock", target = "stock")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   Inventory toDomain(InventoryRequestDto dto);

   @Mapping(source = "branchOfficeId", target = "branchOfficeId")
   @Mapping(source = "productId", target = "productId")
   @Mapping(source = "productPrice", target = "price")
   @Mapping(source = "stock", target = "stock")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   InventoryDocument toEntity(Inventory domain);

   @Mapping(source = "id", target = "id")
   @Mapping(source = "branchOfficeId", target = "branchOfficeId")
   @Mapping(source = "productId", target = "productId")
   @Mapping(source = "price", target = "productPrice")
   @Mapping(source = "stock", target = "stock")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   Inventory toDomain(InventoryDocument document);

   @Mapping(source = "id", target = "id")
   @Mapping(source = "branchOfficeId", target = "branchOfficeId")
   @Mapping(source = "productId", target = "productId")
   @Mapping(source = "productPrice", target = "productPrice")
   @Mapping(source = "stock", target = "stock")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   InventoryResponseDto toResponseDto(Inventory inventory);
}
