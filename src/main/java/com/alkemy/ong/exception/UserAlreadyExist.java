package com.alkemy.ong.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String error) {
        super(error);
    }
}
