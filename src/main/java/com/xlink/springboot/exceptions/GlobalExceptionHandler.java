/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xlink.springboot.exceptions;

import com.xlink.springboot.util.JsonEnvelope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class.getName());

    private final Map<String, String> integrityErrorTemplate = Map.of(
            "isbn", "Book with isbn = %s is already existed"
    );

    private HttpStatus getHttpStatus(CrudException.ExceptionType kind) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (kind) {
            case DataNotFound: {
                status = HttpStatus.NOT_FOUND;
                break;
            }
            case DataIntegrityViolation:
            case DataExisted: {
                status = HttpStatus.BAD_REQUEST;
                break;
            }
        }

        return status;

    }

    @ExceptionHandler(value = {CrudException.class})
    public ResponseEntity<Map<String, Object>> handleCrudException(CrudException ex) {

        Map<String, Object> env;

        env = JsonEnvelope.encloseErrorData(
                getHttpStatus(ex.getKind()).value(),
                ex.getClass().getCanonicalName(),
                ex.getMessage()
        );

        return new ResponseEntity<Map<String, Object>>(env, getHttpStatus(ex.getKind()));

    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Map<String, Object>> handleAllException(Exception ex) {

        LOG.log(Level.INFO, "Exception Classname = {0}", ex.getClass().getCanonicalName());
        LOG.log(Level.INFO, "Exception Message = {0}", ex.getMessage());

        Map<String, Object> env;

        env = JsonEnvelope.encloseErrorData(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getCanonicalName(),
                ex.getMessage()
        );

        return new ResponseEntity<Map<String, Object>>(env, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
