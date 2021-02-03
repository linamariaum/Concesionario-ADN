package com.ceiba.concessionnaire.dominio.dto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;

import java.util.Date;

public class Venta {

    private final Date fecha;
    private final Moto moto;
    private final String cedulaCliente;
    private Date fechaEntrega;

    public Venta(Date fecha, Moto moto, String cliente, Date fechaEntrega) {
        this.fecha = (Date) fecha.clone();
        this.moto = moto;
        this.cedulaCliente = cliente;
        this.fechaEntrega = (Date) fechaEntrega.clone();
    }

    public Date getFecha() {
        return (Date) fecha.clone();
    }

    public Moto getMoto() {
        return moto;
    }

    public String getCliente() {
        return cedulaCliente;
    }

    public Date getFechaEntrega() {
        return (Date) fechaEntrega.clone();
    }
}
