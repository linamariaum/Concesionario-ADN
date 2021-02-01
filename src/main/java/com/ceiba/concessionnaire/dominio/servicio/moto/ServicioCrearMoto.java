package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import org.springframework.stereotype.Component;

@Component
public class ServicioCrearMoto {

    private final RepositorioMoto repositorioMoto;

    public ServicioCrearMoto(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public void ejecutar(Moto moto) { this.repositorioMoto.agregar(moto); }

}
