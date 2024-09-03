package com.RESTAPI.demo.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculationDto {

    @Size(max=255, message = "username은 255자 이내이어야합니다.")
    private String username;
    @Size(max=255, message = "수식은 255자 이내이어야합니다.")
    private String formula;
}
