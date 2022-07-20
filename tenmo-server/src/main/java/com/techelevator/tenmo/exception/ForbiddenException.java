package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You shall not pass.")
public class ForbiddenException extends Exception {
    private static final long serialVersionUID =1L;

    public ForbiddenException() {
        super("You shall not pass.");
    }
}
