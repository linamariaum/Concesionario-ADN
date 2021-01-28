package com.ceiba.concessionnaire.aplicacion.manejadores.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.servicio.asesor.ServicioAsesor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarVenta {

    private final ServicioAsesor servicioAsesor;

    public ManejadorGenerarVenta(ServicioAsesor servicioAsesor) {
        this.servicioAsesor = servicioAsesor;
    }

    @Transactional
    public Venta ejecutar(String placa, String cedulaCliente) {
        return this.servicioAsesor.vender(placa, cedulaCliente);
    }
}
