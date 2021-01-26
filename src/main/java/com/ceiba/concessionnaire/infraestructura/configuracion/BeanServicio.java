package com.ceiba.concessionnaire.infraestructura.configuracion;

import com.ceiba.concessionnaire.dominio.repositorio.RepositorioMoto;
import com.ceiba.concessionnaire.dominio.repositorio.RepositorioVenta;
import com.ceiba.concessionnaire.dominio.servicio.asesor.ServicioAsesor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioAsesor servicioAsesor(RepositorioVenta repositorioVenta, RepositorioMoto repositorioMoto) {
        return new ServicioAsesor(repositorioVenta, repositorioMoto);
    }
}
