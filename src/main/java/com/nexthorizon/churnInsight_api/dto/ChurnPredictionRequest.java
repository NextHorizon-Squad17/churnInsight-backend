package com.nexthorizon.churnInsight_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChurnPredictionRequest {
    @NotNull
    private Integer tempo_contrato_meses;

    @NotNull
    private Integer atrasos_pagamento;

    @NotNull
    private Double uso_mensal;

    @NotNull
    private String plano;
}
