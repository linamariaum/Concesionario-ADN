package com.ceiba.concessionnaire.infraestructura.persistencia.entidad;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Venta")
@NamedQuery(name = "Venta.findByCedulaCliente", query = "SELECT venta FROM Venta venta WHERE venta.cedulaCliente = :cedulaCliente")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String cedulaCliente;

    @CreationTimestamp
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "ID_MOTO", referencedColumnName = "id")
    private MotoEntity moto;

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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MotoEntity getMoto() {
        return moto;
    }

    //public void setMoto(MotoEntity moto) {
       // this.moto = moto;
   // }
}
