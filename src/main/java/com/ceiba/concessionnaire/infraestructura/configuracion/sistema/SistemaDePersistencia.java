package com.ceiba.concessionnaire.infraestructura.configuracion.sistema;

import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.RepositorioMotoPersistente;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.RepositorioVentaPersistente;

import javax.persistence.EntityManager;

public class SistemaDePersistencia {

    private final EntityManager entityManager;

    public SistemaDePersistencia(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public RepositorioMoto obtenerRepositorioMotos() {
        return new RepositorioMotoPersistente(entityManager);
    }

    public RepositorioVenta obtenerRepositorioVentas() {
        return new RepositorioVentaPersistente(entityManager, this.obtenerRepositorioMotos());
    }

    public void iniciar() {
        entityManager.getTransaction().begin();
    }

    public void terminar() {
        entityManager.getTransaction().commit();
    }
}
