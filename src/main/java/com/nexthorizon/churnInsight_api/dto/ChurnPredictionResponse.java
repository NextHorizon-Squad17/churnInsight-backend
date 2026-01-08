package com.nexthorizon.churnInsight_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChurnPredictionResponse {
    private String previsao;
    private Double probabilidade;
}
