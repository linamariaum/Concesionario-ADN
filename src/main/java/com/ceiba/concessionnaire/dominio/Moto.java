package com.ceiba.concessionnaire.dominio;

public class Moto {

    private final String placa;
    private final String marca;
    private final String modelo;
    private String color;
    private int precio;

    public Moto(String placa, String marca, String modelo, String color, int precio) {
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

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
