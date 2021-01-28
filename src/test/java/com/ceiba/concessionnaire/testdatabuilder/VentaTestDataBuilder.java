package com.ceiba.concessionnaire.testdatabuilder;

import com.ceiba.concessionnaire.dominio.dto.Venta;

import java.util.Date;

public class VentaTestDataBuilder {

    private static final String CLIENTE = "1234567890";

    public Venta build() {
        return new Venta(new Date(), new MotoTestDataBuilder().build(), CLIENTE, new Date());
    }
}
