package com.nequi.challenge.contexts.franchise.infrastructure.adapters.dto;

import java.time.LocalDateTime;

public record BranchOfficeResponseDto(
      String id,
      String name,
      LocalDateTime created_at,
      LocalDateTime updated_at
) {}
