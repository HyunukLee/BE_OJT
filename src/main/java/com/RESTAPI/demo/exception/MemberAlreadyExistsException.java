package com.RESTAPI.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class MemberAlreadyExistsException extends RuntimeException {

    private String message;

    public MemberAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
