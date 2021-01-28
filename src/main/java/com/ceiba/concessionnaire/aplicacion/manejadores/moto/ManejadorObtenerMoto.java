package com.ceiba.concessionnaire.aplicacion.manejadores.moto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMoto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManejadorObtenerMoto {

    private final ServicioObtenerMoto servicioObtenerMoto;

    public ManejadorObtenerMoto(ServicioObtenerMoto servicioObtenerMoto) {
        this.servicioObtenerMoto = servicioObtenerMoto;
    }

    @Transactional
    public Moto ejecutar(String placa) {
        return this.servicioObtenerMoto.ejecutar(placa);
    }
}
