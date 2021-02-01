package com.ceiba.concessionnaire.infraestructura.error;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String msg) {
        super(msg);
    }
}
