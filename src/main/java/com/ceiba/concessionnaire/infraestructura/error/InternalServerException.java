package com.ceiba.concessionnaire.infraestructura.error;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String msg, Exception err) {
        super(msg);
    }
}
