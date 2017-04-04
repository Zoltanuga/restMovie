package com.movie.controller;

import com.movie.controller.exceptions.DataException;
import com.movie.model.responseObjects.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;


@RestControllerAdvice
public class ControllerExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatchException() {
        return new ErrorResponse("incorrect type of the request parameter", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidDataException(DataException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
