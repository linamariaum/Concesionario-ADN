package com.ceiba.concessionnaire.aplicacion.manejadores.venta;

import com.ceiba.concessionnaire.dominio.Venta;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioObtenerVentas;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorObtenerVentas {

    private final ServicioObtenerVentas servicioObtenerVentas;

    public ManejadorObtenerVentas(ServicioObtenerVentas servicioObtenerVentas) {
        this.servicioObtenerVentas = servicioObtenerVentas;
    }

    public List<Venta> ejecutar(String cedulaCliente) {
        return this.servicioObtenerVentas.ejecutar(cedulaCliente);
    }
}
