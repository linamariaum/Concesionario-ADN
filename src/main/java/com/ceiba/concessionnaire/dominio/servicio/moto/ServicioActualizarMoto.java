package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import org.springframework.stereotype.Component;

@Component
public class ServicioActualizarMoto {

    private final RepositorioMoto repositorioMoto;

    public ServicioActualizarMoto(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public Moto ejecutar(int id, Moto moto) {
        return this.repositorioMoto.actualizar(id, moto);
    }

}

