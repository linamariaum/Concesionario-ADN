package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.RepositorioMotoPersistente;
import org.springframework.stereotype.Component;

@Component
public class ServicioObtenerMoto {

    private final RepositorioMoto repositorioMoto;

    public ServicioObtenerMoto(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public Moto ejecutar(String placa) {
        return this.repositorioMoto.obtenerPorPlaca(placa);
    }
}
