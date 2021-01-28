package com.ceiba.concessionnaire.infraestructura.configuracion.sistema;

import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.RepositorioMotoPersistente;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.RepositorioVentaPersistente;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioVentaJPA;

import javax.persistence.EntityManager;

public class SistemaDePersistencia {

    private final EntityManager entityManager;
    //private final RepositorioMotoJPA repositorioMotoJPA;
    private final RepositorioVentaJPA repositorioVentaJPA;

    public SistemaDePersistencia(EntityManager entityManager, RepositorioVentaJPA repositorioVentaJPA) {
        this.entityManager = entityManager;
        //this.repositorioMotoJPA = repositorioMotoJPA;
        this.repositorioVentaJPA = repositorioVentaJPA;
    }

    public RepositorioMoto obtenerRepositorioMotos() {
        return new RepositorioMotoPersistente(entityManager);
    }

    public RepositorioVenta obtenerRepositorioVentas() {
        return new RepositorioVentaPersistente(entityManager, repositorioVentaJPA);
    }

    public void iniciar() {
        entityManager.getTransaction().begin();
    }

    public void terminar() {
        entityManager.getTransaction().commit();
    }
}
