package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;

import java.util.List;

public class ServicioObtenerMotos {

    private final RepositorioMoto repositorioMoto;

    public ServicioObtenerMotos(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public List<Moto> ejecutar() {
        return this.repositorioMoto.obtenerMotos();
    }
}
