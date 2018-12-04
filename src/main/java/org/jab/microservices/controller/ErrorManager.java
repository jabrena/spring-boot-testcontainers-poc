package org.jab.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorManager {

    private Logger log = LoggerFactory.getLogger(ErrorManager.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void generalExeption(HttpServletRequest request, Exception ex) {
        log.error("{} : {}", ex.getMessage(), request.getRequestURI());
    }
}