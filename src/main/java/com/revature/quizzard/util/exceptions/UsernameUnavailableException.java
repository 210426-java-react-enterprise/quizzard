package com.revature.quizzard.util.exceptions;

public class UsernameUnavailableException extends ResourcePersistenceException {
    public UsernameUnavailableException() {
        super("The provided username is already taken!");
    }
}
