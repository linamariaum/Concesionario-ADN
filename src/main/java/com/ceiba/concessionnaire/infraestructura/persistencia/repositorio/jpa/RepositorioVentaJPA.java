package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa;

import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioVentaJPA extends JpaRepository<VentaEntity, Integer> {

    /**
     * Permite obtener las ventas entity por cedula cliente
     *
     * @param cedulaCliente
     * @return List<VentaEntity>
     */
    List<VentaEntity> findAllByCedulaClienteOrderByIdDesc(String cedulaCliente);

}
