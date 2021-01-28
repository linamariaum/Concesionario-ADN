package com.ceiba.concessionnaire.dominio.unitaria;

import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.testdatabuilder.MotoTestDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotoTest {

    private static final int PRECIO = 2020;
    private static final String PLACA = "DUH43A";
    private static final String MODELO = "TEC123";
    private static final String MARCA = "TEC";
    private static final String COLOR = "NEGRO";

    @Test
    public void crearMotoTest() {

        // arrange
        MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
                conPlaca(PLACA).
                conModelo(MODELO).
                conMarca(MARCA).
                conColor(COLOR).
                conPrecio(PRECIO);

        // act
        Moto moto = motoTestDataBuilder.build();

        // assert
        assertEquals(PLACA, moto.getPlaca());
        assertEquals(MODELO, moto.getModelo());
        assertEquals(MARCA, moto.getMarca());
        assertEquals(COLOR, moto.getModelo());
        assertEquals(PRECIO, moto.getPrecio());
    }
}
