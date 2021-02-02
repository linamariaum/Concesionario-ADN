package com.ceiba.concessionnaire.aplicacion.manejadores.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioCrearVenta;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarVenta {

    private final ServicioCrearVenta servicioCrearVenta;

    public ManejadorGenerarVenta(ServicioCrearVenta servicioCrearVenta) {
        this.servicioCrearVenta = servicioCrearVenta;
    }

    @Transactional
    public Venta ejecutar(String placa, String cedulaCliente) {
        return this.servicioCrearVenta.vender(placa, cedulaCliente);
    }
}
