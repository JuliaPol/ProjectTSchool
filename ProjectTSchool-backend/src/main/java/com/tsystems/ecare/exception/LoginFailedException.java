package com.tsystems.ecare.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login or password is incorrect");
    }
}
