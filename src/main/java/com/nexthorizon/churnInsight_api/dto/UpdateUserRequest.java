package com.nexthorizon.churnInsight_api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(@NotBlank String name, String password) {}
