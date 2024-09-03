package com.RESTAPI.demo.exception.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class ApiExceptionEntity{
    private String errorMessage;
    private HttpStatus status;

    @Builder
    public ApiExceptionEntity(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
