package com.ceiba.concessionnaire.dominio.unitaria;

import com.ceiba.concessionnaire.dominio.exception.BadDataException;
import com.ceiba.concessionnaire.dominio.modelo.Moto;
import com.ceiba.concessionnaire.testdatabuilder.MotoTestDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MotoTest {

    private static final int PRECIO = 2020;
    private static final String PLACA = "DUH43A";
    private static final String MODELO = "TEC123";
    private static final String MARCA = "TEC";
    private static final String COLOR = "NEGRO";
    private static final String PLACA_ERRONEA = "43AS3F";
    private static final int PRECIO_INFERIOR_AL_PERMITIDO = 500;

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
        assertEquals(COLOR, moto.getColor());
        assertEquals(PRECIO, moto.getPrecio());
    }

    @Test
    public void crearMotoSinFormatoPlacaTest() {

        // arrange
        MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
                conPlaca(PLACA_ERRONEA).
                conModelo(MODELO).
                conMarca(MARCA).
                conColor(COLOR).
                conPrecio(PRECIO);

        // act
        //Moto moto = motoTestDataBuilder.build();

        // assert
        Throwable throwable = assertThrows(BadDataException.class, motoTestDataBuilder::build);
        assertEquals("Placa inválida", throwable.getMessage());
    }

    @Test
    public void crearMotoConPrecioErroneoTest() {

        MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
                conPlaca(PLACA).
                conModelo(MODELO).
                conMarca(MARCA).
                conColor(COLOR).
                conPrecio(PRECIO_INFERIOR_AL_PERMITIDO);

        Throwable throwable = assertThrows(BadDataException.class, motoTestDataBuilder::build);
        assertEquals("Precio inválido", throwable.getMessage());
    }
}
