package com.ceiba.concessionnaire.infraestructura.controlador;

import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorGenerarVenta;
import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorObtenerVentas;
import com.ceiba.concessionnaire.aplicacion.manejadores.venta.ManejadorObtenerVentasPorCliente;
import com.ceiba.concessionnaire.dominio.dto.Venta;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class ControladorVenta {

    private final ManejadorGenerarVenta manejadorGenerarVenta;
    private final ManejadorObtenerVentas manejadorObtenerVentas;
    private final ManejadorObtenerVentasPorCliente manejadorObtenerVentasPorCliente;

    public ControladorVenta(ManejadorGenerarVenta manejadorGenerarVenta, ManejadorObtenerVentas manejadorObtenerVentas, ManejadorObtenerVentasPorCliente manejadorObtenerVentasPorCliente) {
        this.manejadorGenerarVenta = manejadorGenerarVenta;
        this.manejadorObtenerVentas = manejadorObtenerVentas;
        this.manejadorObtenerVentasPorCliente = manejadorObtenerVentasPorCliente;
    }

    @PostMapping("/{placa}/{cedulaCliente}")
    public Venta vender(@PathVariable(name = "placa") String placa,
                        @PathVariable(name = "cedulaCliente") String cedulaCliente) {
        return this.manejadorGenerarVenta.ejecutar(placa, cedulaCliente);
    }

    @GetMapping("/{cedulaCliente}")
    public List<Venta> obtenerVentasPorCliente(@PathVariable(name = "cedulaCliente") String cedulaCliente) {
        return this.manejadorObtenerVentasPorCliente.ejecutar(cedulaCliente);
    }

    @GetMapping
    public List<Venta> obtenerVentas() {
        return this.manejadorObtenerVentas.ejecutar();
    }
}
