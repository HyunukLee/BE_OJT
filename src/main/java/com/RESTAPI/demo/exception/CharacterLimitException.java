package com.RESTAPI.demo.exception;

public class CharacterLimitException extends RuntimeException {

    private String message;

    public CharacterLimitException(String message) {
        super(message);
        this.message = message;
    }
}
