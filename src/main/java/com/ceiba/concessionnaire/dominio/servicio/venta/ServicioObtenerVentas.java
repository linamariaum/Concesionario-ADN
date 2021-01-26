package com.ceiba.concessionnaire.dominio.servicio.venta;

import com.ceiba.concessionnaire.dominio.Moto;
import com.ceiba.concessionnaire.dominio.Venta;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

import java.util.List;

public class ServicioObtenerVentas {

    private final RepositorioVenta repositorioVenta;

    public ServicioObtenerVentas(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public List<Venta> ejecutar(String cedula) {
        return this.repositorioVenta.obtenerVentasPorCedulaCliente(cedula);
    }

}
