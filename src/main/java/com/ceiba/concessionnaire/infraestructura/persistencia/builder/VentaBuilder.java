package com.ceiba.concessionnaire.infraestructura.persistencia.builder;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.VentaEntity;

public final class VentaBuilder {

    private VentaBuilder() {
        throw new IllegalStateException("Clase de traducción");
    }

    public static Venta convertirADominio(VentaEntity ventaEntity) {
        Venta venta = null;
        if (ventaEntity != null) {
            venta = new Venta(ventaEntity.getFecha(), MotoBuilder.convertirADominio(ventaEntity.getMoto()), ventaEntity.getCedulaCliente(), ventaEntity.getFechaEntrega());
        }
        return venta;
    }
}
