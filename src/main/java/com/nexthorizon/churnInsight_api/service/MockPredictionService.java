package com.nexthorizon.churnInsight_api.service;

import com.nexthorizon.churnInsight_api.dto.ChurnPredictionRequest;
import com.nexthorizon.churnInsight_api.dto.ChurnPredictionResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class MockPredictionService implements ChurnPredictionService {

    @Override
    public ChurnPredictionResponse predict(ChurnPredictionRequest request) {

        boolean vaiCancelar =
                request.getAtrasos_pagamento() > 1 ||
                        request.getTempo_contrato_meses() < 6;

        String previsao = vaiCancelar ? "Vai cancelar" : "Vai continuar";

        double probabilidade = vaiCancelar ? 0.81 : 0.25;
        return new ChurnPredictionResponse(previsao, probabilidade);
    }
}
