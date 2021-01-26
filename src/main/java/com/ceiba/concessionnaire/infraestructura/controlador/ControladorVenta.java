package com.ceiba.concessionnaire.infraestructura.controlador;

import com.ceiba.concessionnaire.aplicacion.manejadores.moto.ManejadorObtenerMoto;
import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorGenerarVenta;
import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorObtenerVentas;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class ControladorVenta {

    private final ManejadorGenerarVenta manejadorGenerarVenta;
    private final ManejadorObtenerVentas manejadorObtenerVentas;

    public ControladorVenta(ManejadorGenerarVenta manejadorGenerarVenta, ManejadorObtenerVentas manejadorObtenerVentas) {
        this.manejadorGenerarVenta = manejadorGenerarVenta;
        this.manejadorObtenerVentas = manejadorObtenerVentas;
    }

    @PostMapping("/{placa}/{cedulaCliente}")
    public void vender(@PathVariable(name = "placa") String placa,
                        @PathVariable(name = "cedulaCliente") String cedulaCliente) {
        this.manejadorGenerarVenta.ejecutar(placa, cedulaCliente);
    }
}
