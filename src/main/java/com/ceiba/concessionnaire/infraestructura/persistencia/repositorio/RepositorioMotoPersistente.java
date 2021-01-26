package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.MotoBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioMotoPersistente implements RepositorioMoto {

    private final EntityManager entityManager;
    private final RepositorioMotoJPA repositorioMotoJPA;

    public RepositorioMotoPersistente(EntityManager entityManager, RepositorioMotoJPA repositorioMotoJPA) {
        this.entityManager = entityManager;
        this.repositorioMotoJPA = repositorioMotoJPA;
    }

    @Override
    public List<Moto> obtenerMotos() {
        repositorioMotoJPA.findAll();
        return null;
    }

    @Override
    public Optional<Moto> obtenerPorPlaca(String placa) {

        return null;
    }

    @Override
    public void agregar(Moto moto) {
        entityManager.persist(MotoBuilder.convertirAEntity(moto));
    }




}
