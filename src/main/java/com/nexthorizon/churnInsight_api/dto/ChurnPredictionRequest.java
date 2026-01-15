package com.nexthorizon.churnInsight_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChurnPredictionRequest {

    // Dados Demográficos
    @JsonProperty("Gender")
    @NotNull(message = "Gender is required")
    private String gender; // "Male", "Female"

    @JsonProperty("SeniorCitizen")
    @NotNull
    private Integer seniorCitizen; // 0, 1

    @JsonProperty("Partner")
    @NotNull
    private String partner; // "Yes", "No"

    @JsonProperty("Dependents")
    @NotNull
    private String dependents; // "Yes", "No"

    // Dados de Serviço
    @JsonProperty("TenureMonths")
    @NotNull
    private Integer tenureMonths;

    @JsonProperty("PhoneService")
    @NotNull
    private String phoneService;

    @JsonProperty("MultipleLines")
    @NotNull
    private String multipleLines; // "Yes", "No", "No phone service"

    @JsonProperty("InternetService")
    @NotNull
    private String internetService; // "DSL", "Fiber optic", "No"

    @JsonProperty("OnlineSecurity")
    @NotNull
    private String onlineSecurity;

    @JsonProperty("OnlineBackup")
    @NotNull
    private String onlineBackup;

    @JsonProperty("DeviceProtection")
    @NotNull
    private String deviceProtection;

    @JsonProperty("TechSupport")
    @NotNull
    private String techSupport;

    @JsonProperty("StreamingTV")
    @NotNull
    private String streamingTV;

    @JsonProperty("StreamingMovies")
    @NotNull
    private String streamingMovies;

    // Financeiro e Contrato
    @JsonProperty("Contract")
    @NotNull
    private String contract; // "Month-to-month", "One year", "Two year"

    @JsonProperty("PaperlessBilling")
    @NotNull
    private String paperlessBilling;

    @JsonProperty("PaymentMethod")
    @NotNull
    private String paymentMethod; // "Electronic check", ...

    @JsonProperty("MonthlyCharges")
    @NotNull
    private Double monthlyCharges;

    @JsonProperty("TotalCharges")
    @NotNull
    private Double totalCharges;
}