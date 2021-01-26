package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.Venta;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RepositorioVentaPersistente implements RepositorioVenta {

    private final EntityManager entityManager;
    private final RepositorioMotoJPA repositorioMotoJPA;

    public RepositorioVentaPersistente(EntityManager entityManager, RepositorioMoto repositorioMoto) {
        this.entityManager = entityManager;
        this.repositorioMotoJPA = (RepositorioMotoJPA) repositorioMoto;
    }


    @Override
    public Venta crear(Venta venta) {
        return null;
    }

    @Override
    public List<Venta> obtenerVentasPorCedulaCliente(String cedula) {
        return null;
    }
}
