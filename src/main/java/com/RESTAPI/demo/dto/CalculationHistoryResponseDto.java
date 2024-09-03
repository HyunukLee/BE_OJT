package com.RESTAPI.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class CalculationHistoryResponseDto {

    @JsonProperty("isCorrect")
    private boolean isCorrect;

    private String formula;

    @Builder
    public CalculationHistoryResponseDto(String formula, boolean isCorrect) {
        this.isCorrect = isCorrect;
        this.formula = formula;
    }
}
