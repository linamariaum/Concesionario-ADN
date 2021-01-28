package com.ceiba.concessionnaire.testdatabuilder;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.dominio.modelo.Moto;

public class MotoTestDataBuilder {

    private static final int PRECIO = 2030;
    private static final String PLACA = "DUH43B";
    private static final String MODELO = "CET125";
    private static final String MARCA = "CET";
    private static final String COLOR = "VERDE";

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private int precio;

    public MotoTestDataBuilder() {
        this.placa = PLACA;
        this.marca = MARCA;
        this.modelo = MODELO;
        this.color = COLOR;
        this.precio = PRECIO;
    }

    public MotoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public MotoTestDataBuilder conMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public MotoTestDataBuilder conColor(String color) {
        this.color = color;
        return this;
    }

    public MotoTestDataBuilder conModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public MotoTestDataBuilder conPrecio(int precio) {
        this.precio = precio;
        return this;
    }

    public Moto build() {
        return new Moto(placa,marca,modelo,color, precio);
    }

    public ComandoMoto buildComando() {
        return new ComandoMoto(this.placa, this.marca, this.modelo, this.color, this. precio);
    }
}
