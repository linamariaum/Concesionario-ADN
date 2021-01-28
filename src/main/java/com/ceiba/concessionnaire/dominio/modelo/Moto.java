package com.ceiba.concessionnaire.dominio.modelo;

import com.ceiba.concessionnaire.dominio.exception.BadDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Moto {

    public static final int precioMinimo = 1000;

    private final String placa;
    private final String marca;
    private final String modelo;
    private String color;
    private int precio;

    public Moto(String placa, String marca, String modelo, String color, int precio) {

        if (!this.validarPrecio(precio)) {
            throw new BadDataException("Precio inválido");
        }
        if (!this.validarPlaca(placa)) {
            throw new BadDataException("Placa inválida");
        }
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }

    private boolean validarPrecio(int precio) {
        return precio >= precioMinimo;
    }

    private boolean validarPlaca(String placa) {
        Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{2}[A-Z]{1}");
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
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
