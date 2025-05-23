package com.nequi.challenge.contexts.franchise.domain.model;

import java.time.LocalDateTime;

public record Product(String id, String name, LocalDateTime created_at, LocalDateTime updated_at) {}
