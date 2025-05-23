package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;

public record Franchise(Long id, String name, LocalDateTime created_at, LocalDateTime updated_at) {}
