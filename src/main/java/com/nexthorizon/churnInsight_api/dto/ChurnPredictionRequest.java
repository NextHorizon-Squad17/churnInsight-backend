package com.nexthorizon.churnInsight_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChurnPredictionRequest {

  @NotNull
  @JsonProperty("customer_id")
  private String customerId;
}
