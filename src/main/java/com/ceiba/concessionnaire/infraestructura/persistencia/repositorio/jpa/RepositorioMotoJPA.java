package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa;

import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioMotoJPA extends JpaRepository<MotoEntity, Integer> {

    //MotoEntity
}
