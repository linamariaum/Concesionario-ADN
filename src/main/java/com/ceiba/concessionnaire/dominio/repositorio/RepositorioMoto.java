package com.ceiba.concessionnaire.dominio.repositorio;

import com.ceiba.concessionnaire.dominio.Moto;

import java.util.List;

public interface RepositorioMoto {

    /**
     * Permite obtener todas las motos
     *
     * @return List<Moto>
     */
    List<Moto> obtenerMotos();

    /**
     * Permite obtener una moto dada una placa
     *
     * @param placa
     * @return Moto
     */
    Moto obtenerPorPlaca(String placa);

    /**
     * Permite agregar una moto al repositorio
     *
     * @param moto
     */
    void agregar(Moto moto);

}