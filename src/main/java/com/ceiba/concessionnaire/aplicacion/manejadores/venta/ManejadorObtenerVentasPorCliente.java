package com.ceiba.concessionnaire.aplicacion.manejadores.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioObtenerVentasPorCliente;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ManejadorObtenerVentasPorCliente {

    private ServicioObtenerVentasPorCliente servicioObtenerVentasPorCliente;

    public ManejadorObtenerVentasPorCliente(ServicioObtenerVentasPorCliente servicioObtenerVentasPorCliente) {
        this.servicioObtenerVentasPorCliente = servicioObtenerVentasPorCliente;
    }

    public List<Venta> ejecutar(String cedula) {
        return this.servicioObtenerVentasPorCliente.ejecutar(cedula);
    }

}
