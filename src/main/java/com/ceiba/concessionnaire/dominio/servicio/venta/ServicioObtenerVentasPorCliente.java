package com.ceiba.concessionnaire.dominio.servicio.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

import java.util.List;

public class ServicioObtenerVentasPorCliente {

    private final RepositorioVenta repositorioVenta;

    public ServicioObtenerVentasPorCliente(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public List<Venta> ejecutar(String cedula) {
        return this.repositorioVenta.obtenerVentasPorCedulaCliente(cedula);
    }
}
