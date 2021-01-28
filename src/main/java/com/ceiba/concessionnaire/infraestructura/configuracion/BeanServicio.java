package com.ceiba.concessionnaire.infraestructura.configuracion;

import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.dominio.servicio.asesor.ServicioAsesor;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMoto;
import com.ceiba.concessionnaire.dominio.servicio.moto.ServicioObtenerMotos;
import com.ceiba.concessionnaire.dominio.servicio.venta.ServicioObtenerVentas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioAsesor servicioAsesor(RepositorioVenta repositorioVenta, RepositorioMoto repositorioMoto) {
        return new ServicioAsesor(repositorioVenta, repositorioMoto);
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

}
