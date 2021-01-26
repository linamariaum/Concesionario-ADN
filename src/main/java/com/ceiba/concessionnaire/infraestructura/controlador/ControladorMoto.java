package com.ceiba.concessionnaire.infraestructura.controlador;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.aplicacion.manejadores.moto.ManejadorCrearMoto;
import com.ceiba.concessionnaire.aplicacion.manejadores.moto.ManejadorObtenerMoto;
import com.ceiba.concessionnaire.aplicacion.manejadores.moto.ManejadorObtenerMotos;
import com.ceiba.concessionnaire.dominio.Moto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class ControladorMoto {
    private final ManejadorCrearMoto manejadorCrearMoto;
    private final ManejadorObtenerMoto manejadorObtenerMoto;
    private final ManejadorObtenerMotos manejadorObtenerMotos;

    public ControladorMoto(ManejadorCrearMoto manejadorCrearMoto, ManejadorObtenerMoto manejadorObtenerMoto, ManejadorObtenerMotos manejadorObtenerMotos) {
        this.manejadorCrearMoto = manejadorCrearMoto;
        this.manejadorObtenerMoto = manejadorObtenerMoto;
        this.manejadorObtenerMotos = manejadorObtenerMotos;
    }

    @PostMapping
    public void agregar(@RequestBody ComandoMoto comandoMoto) {
        this.manejadorCrearMoto.ejecutar(comandoMoto);
    }

    @GetMapping
    public List<Moto> buscar() {
        return this.manejadorObtenerMotos.ejecutar();
    }

    @GetMapping("/{placa}")
    public Moto buscarPorPlaca(@PathVariable(name = "placa") String placa) {
        return this.manejadorObtenerMoto.ejecutar(placa);
    }
}
