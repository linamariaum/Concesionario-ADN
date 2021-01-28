package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa;

import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioMotoJPA extends JpaRepository<MotoEntity, Integer> {

    /**
     * Permite obtener una moto entity por placa
     *
     * @param placa
     * @return MotoEntity
     */
    @Query("select p from Moto p where p.placa = :placa ")
    MotoEntity findByPlaca(@Param("placa") String placa);
}
