package com.RESTAPI.demo.exception;

public class MemberWrongPasswordException extends RuntimeException{

    private String message;

    public MemberWrongPasswordException(String message) {
        super(message);
        this.message = message;
    }
}
