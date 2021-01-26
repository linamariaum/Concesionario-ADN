package com.ceiba.concessionnaire.dominio;

import java.util.Date;

public class Venta {

    private final Date fecha;
    private final Moto moto;
    private final String cedulaCliente;
    private Date fechaEntrega;

    public Venta(Date fecha, Moto moto, String cliente, Date fechaEntrega) {
        this.fecha = fecha;
        this.moto = moto;
        this.cedulaCliente = cliente;
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public Moto getMoto() {
        return moto;
    }

    public String getCliente() {
        return cedulaCliente;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
