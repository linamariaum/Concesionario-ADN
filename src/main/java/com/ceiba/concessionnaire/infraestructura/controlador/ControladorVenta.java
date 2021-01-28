package com.ceiba.concessionnaire.infraestructura.controlador;

import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorGenerarVenta;
import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorObtenerVentas;
import com.ceiba.concessionnaire.dominio.dto.Venta;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Venta vender(@PathVariable(name = "placa") String placa,
                        @PathVariable(name = "cedulaCliente") String cedulaCliente) {
        return this.manejadorGenerarVenta.ejecutar(placa, cedulaCliente);
    }

    @GetMapping
    public List<Venta> buscarVentas() {
        return this.manejadorObtenerVentas.ejecutar();
    }
}
