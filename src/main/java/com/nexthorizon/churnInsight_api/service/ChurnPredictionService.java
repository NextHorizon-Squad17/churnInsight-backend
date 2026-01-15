package com.nexthorizon.churnInsight_api.service;

import com.nexthorizon.churnInsight_api.dto.ChurnPredictionRequest;
import com.nexthorizon.churnInsight_api.dto.ChurnPredictionResponse;

public interface ChurnPredictionService {
  ChurnPredictionResponse predict(ChurnPredictionRequest request);
}
