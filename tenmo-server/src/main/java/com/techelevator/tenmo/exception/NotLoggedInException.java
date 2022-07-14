package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You are not logged in.")
public class NotLoggedInException extends Exception {
    private static final long serialVersionUID =1L;

    public NotLoggedInException() {
        super("You are not logged in.");
    }
}
