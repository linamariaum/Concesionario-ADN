package com.ceiba.concessionnaire.aplicacion.manejadores.moto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMotos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManejadorObtenerMotos {

    private final ServicioObtenerMotos servicioObtenerMotos;

    public ManejadorObtenerMotos(ServicioObtenerMotos servicioObtenerMotos) {
        this.servicioObtenerMotos = servicioObtenerMotos;
    }

    @Transactional
    public List<Moto> ejecutar() {
        return this.servicioObtenerMotos.ejecutar();
    }
}
