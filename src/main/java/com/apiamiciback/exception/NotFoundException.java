package com.apiamiciback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Instantiates a new Not found exception.
     *
     * @param exception the exception
     */
    public NotFoundException(String exception) {
        super(exception);
    }
}
