package com.ceiba.concessionnaire.dominio;

import java.util.Date;

public class Venta {

    private final Date fecha;
    private final Moto moto;
    private final String cedulaCliente;

    public Venta(Date fecha, Moto moto, String cliente) {
        this.fecha = fecha;
        this.moto = moto;
        this.cedulaCliente = cliente;
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

}
