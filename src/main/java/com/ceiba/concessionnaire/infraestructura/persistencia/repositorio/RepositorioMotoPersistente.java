package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.exception.DataDuplicationException;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.exception.DataNotFoundException;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.infraestructura.error.InternalServerException;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.MotoBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioMotoPersistente implements RepositorioMoto {

    @Autowired
    private RepositorioMotoJPA repositorioMotoJPA;

    @Override
    public List<Moto> obtenerMotos() {
        List<MotoEntity> motosEntities = this.repositorioMotoJPA.findAll();
        if (motosEntities.isEmpty()) {
            throw new DataNotFoundException("No hay motos en la base de datos");
        }
        List<Moto> motos = new ArrayList<>();
        for (MotoEntity motoEntity: motosEntities) {
            motos.add(MotoBuilder.convertirADominio(motoEntity));
        }
        return motos;
    }

    @Override
    public Moto obtenerPorPlaca(String placa) {
        Optional<MotoEntity> motoEntityOptional = this.repositorioMotoJPA.findByPlaca(placa);
        if (motoEntityOptional.isPresent()) {
            return MotoBuilder.convertirADominio(motoEntityOptional.get());
        }
        throw new DataNotFoundException("No se encuentra la moto con la placa ingresada");
    }

    @Override
    public void agregar(Moto moto) {
        try {
            Optional<MotoEntity> motoEntityOptional = this.repositorioMotoJPA.findByPlaca(moto.getPlaca());
            if (motoEntityOptional.isPresent()) {
                throw new DataDuplicationException("La motocicleta ya existe.");
            }
            this.repositorioMotoJPA.save(MotoBuilder.convertirAEntity(moto));
        } catch (Exception err) {
            throw new InternalServerException("No se pudo agregar la motocicleta.", err);
        }
    }

    @Override
    public Moto actualizar(String placa, Moto moto) {
        if (placa.equals(moto.getPlaca())) {
            Optional<MotoEntity> motoEntityOptional = this.repositorioMotoJPA.findByPlaca(placa);
            if (motoEntityOptional.isPresent()) {
                MotoEntity motoEntityUpdate = MotoBuilder.convertirAEntity(moto);
                motoEntityUpdate.setId(motoEntityOptional.get().getId());
                MotoEntity m = this.repositorioMotoJPA.save(motoEntityUpdate);
                return MotoBuilder.convertirADominio(m);
            }
        }
        throw new DataNotFoundException("No se encuentra la moto con la placa ingresada");
    }

}
