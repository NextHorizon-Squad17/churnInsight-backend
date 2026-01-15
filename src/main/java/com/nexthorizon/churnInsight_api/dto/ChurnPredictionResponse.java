package com.nexthorizon.churnInsight_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChurnPredictionResponse {

    @JsonProperty("prediction")
    private Integer prediction; // 0 ou 1

    @JsonProperty("probability")
    private Double probability; // 0.0 a 1.0

    @JsonProperty("risk_level")
    private String riskLevel; // "BAIXO", "MÉDIO", "ALTO"

    @JsonProperty("retention_strategy")
    private String retentionStrategy; // O texto gerado pela IA
}