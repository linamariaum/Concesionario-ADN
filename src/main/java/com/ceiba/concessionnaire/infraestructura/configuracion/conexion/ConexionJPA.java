package com.ceiba.concessionnaire.infraestructura.configuracion.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {

    private static EntityManagerFactory entityManagerFactory;

    public ConexionJPA() {
        Persistence.createEntityManagerFactory("concesionario");
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
