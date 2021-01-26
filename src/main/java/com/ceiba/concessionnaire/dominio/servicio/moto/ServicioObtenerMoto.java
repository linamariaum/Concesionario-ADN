package com.ceiba.concessionnaire.dominio.servicio.moto;

import com.ceiba.concessionnaire.dominio.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServicioObtenerMoto {

    private final RepositorioMoto repositorioMoto;

    public ServicioObtenerMoto(RepositorioMoto repositorioMoto) {
        this.repositorioMoto = repositorioMoto;
    }

    public Optional<Moto> ejecutar(String placa) {
        return this.repositorioMoto.obtenerPorPlaca(placa);
    }
}
