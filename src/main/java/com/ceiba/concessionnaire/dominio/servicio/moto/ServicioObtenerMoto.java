package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.exception.DataNotFoundException;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;

public class ServicioObtenerMoto {

    private final RepositorioMoto repositorioMoto;

    public ServicioObtenerMoto(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public Moto ejecutar(String placa) {
        return this.repositorioMoto.obtenerPorPlaca(placa);
    }
}
