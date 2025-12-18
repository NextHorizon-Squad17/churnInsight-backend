package com.nexthorizon.churnInsight_api.dto;

public class ChurnPredictResponse {
    private String prediction; // "CHURN" | "RETAIN"
    private Double probability; // 0.0 - 1.0
}
