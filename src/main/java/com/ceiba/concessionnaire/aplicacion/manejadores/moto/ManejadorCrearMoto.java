package com.ceiba.concessionnaire.aplicacion.manejadores.moto;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.aplicacion.fabrica.FabricaMoto;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioCrearMoto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorCrearMoto {

    private final ServicioCrearMoto servicioCrearMoto;
    private final FabricaMoto fabricaMoto;

    public ManejadorCrearMoto(ServicioCrearMoto servicioCrearMoto, FabricaMoto fabricaMoto) {
        this.servicioCrearMoto = servicioCrearMoto;
        this.fabricaMoto = fabricaMoto;
    }

    @Transactional
    public void ejecutar(ComandoMoto comandoMoto) {
        Moto moto = this.fabricaMoto.crearMoto(comandoMoto);
        this.servicioCrearMoto.ejecutar(moto);
    }
}
