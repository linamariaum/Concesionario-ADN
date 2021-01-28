package com.ceiba.concessionnaire.infraestructura.persistencia.builder;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.VentaEntity;

public class VentaBuilder {

    public VentaBuilder() {
    }

    public static Venta convertirADominio(VentaEntity ventaEntity) {
        Venta venta = null;
        if (ventaEntity != null) {
            venta = new Venta(ventaEntity.getFecha(), MotoBuilder.convertirADominio(ventaEntity.getMoto()), ventaEntity.getCedulaCliente(), ventaEntity.getFechaEntrega());
        }
        return venta;
    }
}
