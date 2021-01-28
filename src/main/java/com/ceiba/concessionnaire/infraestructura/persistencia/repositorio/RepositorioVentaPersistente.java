package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.exception.DataNotFoundException;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.MotoBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.VentaBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.VentaEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioVentaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioVentaPersistente implements RepositorioVenta {

    private final EntityManager entityManager;
    @Autowired
    private RepositorioMotoJPA repositorioMotoJPA;
    private final RepositorioVentaJPA repositorioVentaJPA;

    public RepositorioVentaPersistente(EntityManager entityManager, RepositorioVentaJPA repositorioVentaJPA) {
        this.entityManager = entityManager;
        this.repositorioVentaJPA = repositorioVentaJPA;
    }

    @Override
    public List<Venta> obtenerVentas() {
        List<VentaEntity> ventaEntities = this.repositorioVentaJPA.findAll();
        if (ventaEntities.size() == 0 ) {
            throw new DataNotFoundException("No hay motos en la base de datos");
        }
        List<Venta> ventas = new ArrayList<Venta>();
        for (VentaEntity ventaEntity: ventaEntities) {
            ventas.add(VentaBuilder.convertirADominio(ventaEntity));
        }
        return ventas;
    }

    @Override
    public Venta crear(Venta venta) {
        VentaEntity ventaEntity = this.buildVentaEntity(venta);
        if (ventaEntity == null) {
            throw new DataNotFoundException("No se encuentra la moto requerida");
        }
        entityManager.persist(ventaEntity);
        entityManager.flush();
        return venta;
    }

    @Override
    public List<Venta> obtenerVentasPorCedulaCliente(String cedula) {
        List<VentaEntity> ventaEntities = this.repositorioVentaJPA.findAllByCedulaClienteOrderByIdDesc(cedula);
        List<Venta> ventas = new ArrayList<Venta>();
        if (ventaEntities.size() == 0) {
            return ventas;
        }
        for (VentaEntity venta: ventaEntities) {
            ventas.add(VentaBuilder.convertirADominio(venta));
        }
        return ventas;
    }

    private VentaEntity buildVentaEntity(Venta venta) {
        MotoEntity motoEntityOptional = repositorioMotoJPA.findByPlaca(venta.getMoto().getPlaca());
        if (motoEntityOptional != null) {
            VentaEntity ventaEntity = new VentaEntity(venta.getCliente(), venta.getFecha(), venta.getFechaEntrega(), MotoBuilder.convertirAEntity(venta.getMoto()));
            ventaEntity.setMoto(motoEntityOptional);
            ventaEntity.setFecha(venta.getFecha());
            return ventaEntity;
        }
        return null;
    }
}