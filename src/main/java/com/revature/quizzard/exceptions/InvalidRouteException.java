package com.revature.quizzard.exceptions;

public class InvalidRouteException extends RuntimeException {

    public InvalidRouteException(String route) {
        super("No screen found with provided route: " + route);

    }
}
