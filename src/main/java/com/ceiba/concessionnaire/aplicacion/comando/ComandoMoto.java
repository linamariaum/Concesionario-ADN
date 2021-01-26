package com.ceiba.concessionnaire.aplicacion.comando;

public class ComandoMoto {

    private final String placa;
    private final String marca;
    private final String modelo;
    private String color;
    private int precio;

    public ComandoMoto(String placa, String marca, String modelo, String color, int precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getPrecio() {
        return precio;
    }
}
