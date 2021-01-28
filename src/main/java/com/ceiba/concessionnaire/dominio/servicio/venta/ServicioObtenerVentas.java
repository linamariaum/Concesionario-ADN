package com.ceiba.concessionnaire.dominio.servicio.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

import java.util.List;

public class ServicioObtenerVentas {

    private final RepositorioVenta repositorioVenta;

    public ServicioObtenerVentas(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public List<Venta> ejecutar() {
        return this.repositorioVenta.obtenerVentas();
        //return this.repositorioVenta.obtenerVentasPorCedulaCliente(cedula);
    }

}
