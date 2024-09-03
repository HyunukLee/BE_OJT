package com.RESTAPI.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    @Pattern(regexp = "[a-zA-Z0-9]{0,255}", message = "username은 255자 이내의 영문, 숫자로만 구성되어야 합니다.")
    private String username;
    @Size(max = 255, message = "password는 255자 이내이어야합니다.")
    private String password;
}
