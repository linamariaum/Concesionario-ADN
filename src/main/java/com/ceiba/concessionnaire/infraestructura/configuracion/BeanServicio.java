package com.ceiba.concessionnaire.infraestructura.configuracion;

import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMoto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMotos;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioCrearVenta;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioObtenerVentas;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioObtenerVentasPorCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearVenta servicioCrearVenta(RepositorioVenta repositorioVenta, RepositorioMoto repositorioMoto) {
        return new ServicioCrearVenta(repositorioVenta, repositorioMoto);
    }

    @Bean
    public ServicioObtenerMotos servicioObtenerMotos(RepositorioMoto repositorioMoto) {
        return new ServicioObtenerMotos(repositorioMoto);
    }

    @Bean
    public ServicioObtenerMoto servicioObtenerMoto(RepositorioMoto repositorioMoto) {
        return new ServicioObtenerMoto(repositorioMoto);
    }

    @Bean
    public ServicioObtenerVentas servicioObtenerVentas(RepositorioVenta repositorioVenta) {
        return new ServicioObtenerVentas(repositorioVenta);
    }

    @Bean
    public ServicioObtenerVentasPorCliente servicioObtenerVentasPorCliente(RepositorioVenta repositorioVenta) {
        return new ServicioObtenerVentasPorCliente(repositorioVenta);
    }

}
