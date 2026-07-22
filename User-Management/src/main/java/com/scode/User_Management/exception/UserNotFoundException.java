package com.scode.User_Management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
