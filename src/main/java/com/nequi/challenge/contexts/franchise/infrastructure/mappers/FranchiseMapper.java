package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.Franchise;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.FranchiseRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.FranchiseDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
   Franchise toDomain(FranchiseRequestDto dto);

   FranchiseDocument toEntity(Franchise domain);

   Franchise toDomain(FranchiseDocument document);
}
