package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.exception.DataNotFoundException;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.MotoBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioMotoPersistente implements RepositorioMoto {

    private final EntityManager entityManager;

    @Autowired
    private RepositorioMotoJPA repositorioMotoJPA;

    public RepositorioMotoPersistente(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Moto> obtenerMotos() {
        List<MotoEntity> motosEntities = this.repositorioMotoJPA.findAll();
        if (motosEntities.size() == 0 ) {
            throw new DataNotFoundException("No hay motos en la base de datos");
        }
        List<Moto> motos = new ArrayList<Moto>();
        for (MotoEntity motoEntity: motosEntities) {
            motos.add(MotoBuilder.convertirADominio(motoEntity));
        }
        return motos;
    }

    @Override
    public Moto obtenerPorPlaca(String placa) {
        MotoEntity motoEntityOptional = this.repositorioMotoJPA.findByPlaca(placa);
        if (motoEntityOptional == null) {
            throw new DataNotFoundException("No se encuentra la moto con la placa ingresada");
        }
        return MotoBuilder.convertirADominio(motoEntityOptional);
    }

    @Override
    public void agregar(Moto moto) {
        entityManager.persist(MotoBuilder.convertirAEntity(moto));
        entityManager.flush();
    }

    @Override
    public Moto actualizar(int id, Moto moto) {
        Optional<MotoEntity> motoEntityOptional = this.repositorioMotoJPA.findById(id);
        if (motoEntityOptional.isPresent()) {
            MotoEntity m = this.repositorioMotoJPA.save(MotoBuilder.convertirAEntity(moto));
            return MotoBuilder.convertirADominio(m);
        } else {
            throw new DataNotFoundException("No se encuentra la moto solicitada");
        }
    }

/*    public MotoEntity obtenerMotoEntityPorPlaca(String placa) {
        this.repositorioMotoJPA.obtenerMotoEntityPorPlaca(placa)
                .createNamedQuery(MOTO_FIND_BY_PLACA);
        query.setParameter(PLACA, placa);

        List resultList = query.getResultList();

        return !resultList.isEmpty() ? (MotoEntity) resultList.get(0) : null;
    }*/
}
