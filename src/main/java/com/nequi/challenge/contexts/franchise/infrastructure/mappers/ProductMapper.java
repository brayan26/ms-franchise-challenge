package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.Product;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.ProductRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.ProductResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
   Product toDomain(ProductRequestDto dto);

   ProductDocument toEntity(Product domain);

   @Mapping(source = "id", target = "id")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   Product toDomain(ProductDocument document);

   ProductResponseDto toResponseDto(Product domain);

}
