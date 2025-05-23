package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.FranchiseDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
   Franchise toDomain(FranchiseRequestDto dto);

   FranchiseDocument toEntity(Franchise domain);

   @Mapping(source = "id", target = "id")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   Franchise toDomain(FranchiseDocument document);

   FranchiseResponseDto toResponseDto(Franchise domain);
}
