package com.nexthorizon.churnInsight_api.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userID, String email) {}
