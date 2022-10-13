package com.apiamiciback.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The type App exception handler.
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {


    /**
     * Handle invalid argument map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        log.warn("Wrong params : {}", errorMap);

        return errorMap;
    }

    /**
     * Handle business exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleBusinessException(NotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        log.error("Wrong params INTERNAL SERVER : {}", errorMap);
        return errorMap;
    }
}
