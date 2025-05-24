package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;

public record BranchOffice (String id, String name, String franchiseId,
                            LocalDateTime created_at, LocalDateTime updated_at){
}
