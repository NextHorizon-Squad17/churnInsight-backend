package com.nexthorizon.churnInsight_api.service;

import com.nexthorizon.churnInsight_api.dto.ChurnPredictionRequest;
import com.nexthorizon.churnInsight_api.dto.ChurnPredictionResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock")
public class MockPredictionService implements ChurnPredictionService {

    @Override
    public ChurnPredictionResponse predict(ChurnPredictionRequest request) {
        boolean previsao =
                request.getAtrasos_pagamento() > 1 ||
                request.getTempo_contrato_meses() < 6;

        // TODO: Retornar string de acordo com o contrato definido.
        //  EX: Vai continuar

        double probabilidade = previsao ? 0.75 : 0.25;

        return new ChurnPredictionResponse(previsao, probabilidade);
    }
}
