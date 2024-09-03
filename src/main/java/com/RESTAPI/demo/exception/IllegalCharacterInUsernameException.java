package com.RESTAPI.demo.exception;

public class IllegalCharacterInUsernameException extends RuntimeException {

//    private String message;

    public IllegalCharacterInUsernameException(String message) {
        super(message);
//        this.message = message;
    }
}
