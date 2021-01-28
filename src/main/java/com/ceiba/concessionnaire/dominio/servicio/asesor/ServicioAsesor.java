package com.ceiba.concessionnaire.dominio.servicio.asesor;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.exception.BadDataException;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

import java.util.Calendar;
import java.util.Date;

public class ServicioAsesor {

    private final RepositorioVenta repositorioVenta;
    private final RepositorioMoto repositorioMoto;

    public static final int DIAS_ENTREGA_SEMANA = 3;
    public static final int DIAS_ENTREGA_FIN_DE_SEMANA = 6;
    public static final int INCREMENTO_PRECIO = 5;
    private boolean incremento;

    public ServicioAsesor(RepositorioVenta repositorioVenta, RepositorioMoto repositorioMoto) {
        this.repositorioVenta = repositorioVenta;
        this.repositorioMoto = repositorioMoto;
    }

    public Venta vender(String placa, String cedulaCliente) {
        Date fechaActual = new Date();
        //
        this.diaHabil(fechaActual);

        // Validar compras anteriores

        Moto moto  = this.repositorioMoto.obtenerPorPlaca(placa);
        if (true) {

            Date fechaEntrega = this.calcularEntrega(fechaActual);
            if (incremento) {
                int valorIncremento = ((INCREMENTO_PRECIO*moto.getPrecio())/100);
                moto.setPrecio( valorIncremento + moto.getPrecio());
            }
            Venta venta = new Venta(fechaActual, moto, cedulaCliente, fechaEntrega);
            return this.repositorioVenta.crear(venta);
        }
        throw new UnsupportedOperationException("No se encuentra disponible la moto ingresada");
    }

    private void diaHabil(Date fecha) {
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(fecha);
        if (fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            throw new BadDataException("El día de hoy no se realizan ventas.");
        }
    }

    private Date calcularEntrega(Date fechaActual) {
        Calendar fechaEntrega = Calendar.getInstance();
        fechaEntrega.setTime(fechaActual);
        int dias;
        if (fechaEntrega.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ||
                fechaEntrega.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            dias = DIAS_ENTREGA_FIN_DE_SEMANA;
            this.incremento = true;
        } else {
            dias = DIAS_ENTREGA_SEMANA;
            this.incremento = false;
        }
        int i=1;
        while (i<dias) {
            if (fechaEntrega.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    fechaEntrega.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                fechaEntrega.add(Calendar.DAY_OF_YEAR, 1);
                continue;
            } else {
                fechaEntrega.add(Calendar.DAY_OF_YEAR, 1);
                i++;
            }
        }
        if (fechaEntrega.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            fechaEntrega.add(Calendar.DAY_OF_YEAR, 2);
        }
        return fechaEntrega.getTime();
    }
}
