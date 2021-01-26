package com.ceiba.concessionnaire.dominio.repositorio;

import com.ceiba.concessionnaire.dominio.Venta;

import java.util.List;

public interface RepositorioVenta {

    /**
     * Permite registrar una venta
     *
     * @param venta
     * @return Venta
     */
    Venta crear(Venta venta);

    /**
     * Permite obtener las ventas dada la cedula del cliente
     *
     * @param cedula
     * @return Venta
     */
    List<Venta> obtenerVentasPorCedulaCliente(String cedula);
}
