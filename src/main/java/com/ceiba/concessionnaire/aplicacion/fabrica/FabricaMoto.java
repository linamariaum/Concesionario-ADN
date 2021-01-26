package com.ceiba.concessionnaire.aplicacion.fabrica;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.dominio.Moto;
import org.springframework.stereotype.Component;

@Component
public class FabricaMoto {
    public Moto crearMoto(ComandoMoto comandoMoto) {
        return new Moto(comandoMoto.getPlaca(),comandoMoto.getMarca(), comandoMoto.getModelo(), comandoMoto.getColor(), comandoMoto.getPrecio());
    }
}
