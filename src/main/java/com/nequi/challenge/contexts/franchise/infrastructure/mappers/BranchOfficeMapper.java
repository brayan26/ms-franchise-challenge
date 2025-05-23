package com.nequi.challenge.contexts.franchise.infrastructure.mappers;

import com.nequi.challenge.contexts.franchise.domain.model.BranchOffice;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeRequestDto;
import com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto.BranchOfficeResponseDto;
import com.nequi.challenge.contexts.franchise.infrastructure.persistence.collections.BranchOfficeDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchOfficeMapper {
   BranchOffice toDomain(BranchOfficeRequestDto dto);

   BranchOfficeDocument toEntity(BranchOffice domain);

   BranchOffice toDomain(BranchOfficeDocument document);

   BranchOfficeResponseDto toResponseDto(BranchOffice domain);
}
