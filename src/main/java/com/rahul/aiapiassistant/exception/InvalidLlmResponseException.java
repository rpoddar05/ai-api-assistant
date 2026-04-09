package com.rahul.aiapiassistant.exception;



public class InvalidLlmResponseException extends RuntimeException {

    public InvalidLlmResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
