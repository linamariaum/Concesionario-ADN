package com.ceiba.concessionnaire.aplicacion.manejadores.moto;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.aplicacion.fabrica.FabricaMoto;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioActualizarMoto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ManejadorActualizarMoto {

    private final ServicioActualizarMoto servicioActualizarMoto;
    private final FabricaMoto fabricaMoto;

    public ManejadorActualizarMoto(ServicioActualizarMoto servicioActualizarMoto, FabricaMoto fabricaMoto) {
        this.servicioActualizarMoto = servicioActualizarMoto;
        this.fabricaMoto = fabricaMoto;
    }

    @Transactional
    public Moto ejecutar(String placa, ComandoMoto comandoMoto) {
        Moto moto = this.fabricaMoto.crearMoto(comandoMoto);
        return this.servicioActualizarMoto.ejecutar(placa, moto);
    }
}

