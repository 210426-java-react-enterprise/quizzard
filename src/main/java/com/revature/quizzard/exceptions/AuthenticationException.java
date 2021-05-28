package com.revature.quizzard.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String s) {
        super(s);
    }

    public AuthenticationException() {
        super("Could not authenticate using the provided credentials");
    }
}
