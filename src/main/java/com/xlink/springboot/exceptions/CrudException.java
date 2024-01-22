/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xlink.springboot.exceptions;


import lombok.Getter;

@Getter
public class CrudException extends Exception {

    private final ExceptionType kind;

    public CrudException(ExceptionType kind, String message) {

        super(message);

        this.kind = kind;

    }

    public enum ExceptionType {UncategorizedException, DataIntegrityViolation, DataNotFound, DataExisted}

}
