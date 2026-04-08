package com.ardent.userService.exception;

public class InvalidUserDetailException extends RuntimeException {
    public InvalidUserDetailException(String message) {
        super(message);
    }
}
