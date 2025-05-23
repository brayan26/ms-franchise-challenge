package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchOfficeMapper {
   BranchOffice toDomain(BranchOfficeRequestDto dto);

   BranchOfficeDocument toEntity(BranchOffice domain);

   @Mapping(source = "id", target = "id")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "franchiseId", target = "franchiseId")
   @Mapping(source = "created_at", target = "created_at")
   @Mapping(source = "updated_at", target = "updated_at")
   BranchOffice toDomain(BranchOfficeDocument document);

   BranchOfficeResponseDto toResponseDto(BranchOffice domain);
}
