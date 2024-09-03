package com.RESTAPI.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculationCorrectResponseDto {

    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
