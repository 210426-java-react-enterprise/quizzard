package com.revature.quizzard.exceptions;

public class InvalidRouteException extends RuntimeException {
    public InvalidRouteException() {
        super("No screen found with provided route");
    }
}
