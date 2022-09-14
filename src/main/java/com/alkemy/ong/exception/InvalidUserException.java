package com.alkemy.ong.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String error) {
        super(error);
    }
}
