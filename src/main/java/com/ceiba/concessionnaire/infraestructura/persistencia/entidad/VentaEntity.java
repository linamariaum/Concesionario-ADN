package com.ceiba.concessionnaire.infraestructura.persistencia.entidad;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Venta")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String cedulaCliente;

    @CreationTimestamp
    private Date fecha;

    @Column(nullable = false)
    private Date fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "ID_MOTO", referencedColumnName = "id")
    private MotoEntity moto;

    public VentaEntity(String cedulaCliente, Date fecha, Date fechaEntrega, MotoEntity moto) {
        this.cedulaCliente = cedulaCliente;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.moto = moto;
    }

    public VentaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MotoEntity getMoto() {
        return moto;
    }

    public void setMoto(MotoEntity moto) {
        this.moto = moto;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
