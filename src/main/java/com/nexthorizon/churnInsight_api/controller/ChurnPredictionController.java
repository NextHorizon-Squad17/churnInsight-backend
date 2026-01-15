package com.nexthorizon.churnInsight_api.controller;

import com.nexthorizon.churnInsight_api.dto.ChurnPredictionRequest;
import com.nexthorizon.churnInsight_api.dto.ChurnPredictionResponse;
import com.nexthorizon.churnInsight_api.service.ChurnPredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
@RequiredArgsConstructor
public class ChurnPredictionController {
  private final ChurnPredictionService churnPredictionService;

  @PostMapping
  public ChurnPredictionResponse predict(@Valid @RequestBody ChurnPredictionRequest request) {
    return churnPredictionService.predict(request);
  }
}
