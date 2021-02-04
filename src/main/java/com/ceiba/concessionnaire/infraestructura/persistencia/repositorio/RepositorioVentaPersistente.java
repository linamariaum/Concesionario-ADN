package com.ceiba.concessionnaire.infraestructura.persistencia.repositorio;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.exception.DataNotFoundException;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.MotoBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.builder.VentaBuilder;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.MotoEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.entidad.VentaEntity;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioMotoJPA;
import com.ceiba.concessionnaire.infraestructura.persistencia.repositorio.jpa.RepositorioVentaJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioVentaPersistente implements RepositorioVenta {

    @Autowired
    private RepositorioMotoJPA repositorioMotoJPA;
    private final RepositorioVentaJPA repositorioVentaJPA;

    public RepositorioVentaPersistente(RepositorioVentaJPA repositorioVentaJPA) {
        this.repositorioVentaJPA = repositorioVentaJPA;
    }

    @Override
    public List<Venta> obtenerVentas() {
        List<VentaEntity> ventaEntities = this.repositorioVentaJPA.findAll();
        if (ventaEntities.isEmpty()) {
            throw new DataNotFoundException("No se encontraron ventas.");
        }
        List<Venta> ventas = new ArrayList<>();
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
        VentaEntity v = this.repositorioVentaJPA.save(ventaEntity);
        return VentaBuilder.convertirADominio(v);
    }

    @Override
    public List<Venta> obtenerVentasPorCedulaCliente(String cedula) {
        List<VentaEntity> ventaEntities = this.repositorioVentaJPA.findAllByCedulaClienteOrderByIdDesc(cedula);
        List<Venta> ventas = new ArrayList<>();
        if (ventaEntities.isEmpty()) {
            return ventas;
        }
        for (VentaEntity venta: ventaEntities) {
            ventas.add(VentaBuilder.convertirADominio(venta));
        }
        return ventas;
    }

    private VentaEntity buildVentaEntity(Venta venta) {
        Optional<MotoEntity> motoEntityOptional = repositorioMotoJPA.findByPlaca(venta.getMoto().getPlaca());
        if (motoEntityOptional.isPresent()) {
            MotoEntity motoActualizada = this.actualizarMoto(motoEntityOptional.get(), venta.getMoto());
            VentaEntity ventaEntity = new VentaEntity(venta.getCliente(), venta.getFecha(), venta.getFechaEntrega(), motoActualizada);
            ventaEntity.setMoto(motoActualizada);
            ventaEntity.setFecha(venta.getFecha());
            return ventaEntity;
        }
        return null;
    }

    public MotoEntity actualizarMoto(MotoEntity motoEntity, Moto moto) {
        MotoEntity motoEntityUpdate = MotoBuilder.convertirAEntity(moto);
        motoEntityUpdate.setId(motoEntity.getId());
        return this.repositorioMotoJPA.save(motoEntityUpdate);
    }
}
