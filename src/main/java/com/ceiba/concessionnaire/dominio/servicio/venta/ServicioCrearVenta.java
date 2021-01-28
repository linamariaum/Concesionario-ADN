package com.ceiba.concessionnaire.dominio.servicio.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

public class ServicioCrearVenta {

    private final RepositorioVenta repositorioVenta;

    public ServicioCrearVenta(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public void ejecutar(Venta venta) {
        this.repositorioVenta.crear(venta);
    }
}
