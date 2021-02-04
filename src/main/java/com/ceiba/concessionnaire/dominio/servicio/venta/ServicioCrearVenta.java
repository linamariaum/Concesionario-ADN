package com.ceiba.concessionnaire.dominio.servicio.venta;

import com.ceiba.concessionnaire.dominio.dto.Venta;
import com.ceiba.concessionnaire.dominio.exception.BadDataException;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServicioCrearVenta {


    private final RepositorioVenta repositorioVenta;
    private final RepositorioMoto repositorioMoto;

    public static final int DIAS_ENTREGA_SEMANA = 3;
    public static final int DIAS_ENTREGA_FIN_DE_SEMANA = 6;
    public static final int INCREMENTO_PRECIO = 5;
    public static final int PLAZO_DIAS = 10;
    private boolean incremento;

    public ServicioCrearVenta(RepositorioVenta repositorioVenta, RepositorioMoto repositorioMoto) {
        this.repositorioVenta = repositorioVenta;
        this.repositorioMoto = repositorioMoto;
    }

    public Venta vender(String placa, String cedulaCliente) {
        Date fechaActual = new Date();
        // Validar concesionario abierto
        this.diaHabil(fechaActual);

        // Validar plazo minimo de dompras anteriores
        this.validarComprasAnteriores(cedulaCliente);

        Moto moto  = this.repositorioMoto.obtenerPorPlaca(placa);
        if (moto != null && moto.isDisponible()) {
            Date fechaEntrega = this.calcularEntrega(fechaActual);
            if (incremento) {
                int valorIncremento = ((INCREMENTO_PRECIO*moto.getPrecio())/100);
                moto.setPrecio( valorIncremento + moto.getPrecio());
            }
            moto.setDisponible(false);
            Venta venta = new Venta(fechaActual, moto, cedulaCliente, fechaEntrega);
            return this.repositorioVenta.crear(venta);
        }
        throw new BadDataException("No se encuentra la moto ingresada o no esta disponible.");
    }

    private void diaHabil(Date fecha) {
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(fecha);
        if (fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            throw new BadDataException("El día de hoy no se realizan ventas.");
        }
    }

    private void validarComprasAnteriores(String cedulaCliente) {
        List<Venta> ventas = this.repositorioVenta.obtenerVentasPorCedulaCliente(cedulaCliente);
        if (!ventas.isEmpty()) {
            Venta ultimaCompraCliente = ventas.get(0);
            LocalDateTime fechaActualMenosPlazoDias = LocalDateTime.now().minusDays(PLAZO_DIAS);
            LocalDateTime fechaUltimaCompra = ultimaCompraCliente.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (!fechaActualMenosPlazoDias.isAfter(fechaUltimaCompra)) {
                throw new BadDataException("No se puede realizar la compra dado a que el cliente tiene compra previa. Y no cumple con el plazo de "+PLAZO_DIAS+" días.");
            }
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
