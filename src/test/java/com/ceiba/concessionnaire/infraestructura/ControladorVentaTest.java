package com.ceiba.concessionnaire.infraestructura;

import com.ceiba.concessionnaire.aplicacion.comando.ComandoMoto;
import com.ceiba.concessionnaire.testdatabuilder.MotoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ControladorVentaTest {

    private static final String CLIENTE_EXISTENTE = "1234567890";
    public static final String PLACA_VENDIDA = "ADB34D";
    public static final String PLACA_EXISTENTE = "EFG56H";
    public static final String PLACA_INEXISTENTE = "QWE89T";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void obtenerVentas() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/ventas")
            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cliente").value(CLIENTE_EXISTENTE));
    }

    @Test
    public void obtenerVentasPorCliente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/ventas/{cedulaCliente}", CLIENTE_EXISTENTE)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].cliente").value(CLIENTE_EXISTENTE));
    }

    @Test
    public void crearVenta() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .post("/ventas/{placa}/{cedulaCliente}", PLACA_EXISTENTE, "9870654321")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.cliente").value("9870654321"));
    }

    @Test
    public void crearSegundaVentaSinCumplirPlazoDeDias() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/ventas/{placa}/{cedulaCliente}", PLACA_EXISTENTE, CLIENTE_EXISTENTE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se puede realizar la compra dado a que el cliente tiene compra previa. Y no cumple con el plazo de 10 días."));
    }

    @Test
    public void crearVentaAPlacaYaVendida() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/ventas/{placa}/{cedulaCliente}", PLACA_VENDIDA, "2634567894")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se encuentra la moto ingresada o no esta disponible."));
    }

    @Test
    public void crearVentaConPlacaInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .post("/ventas/{placa}/{cedulaCliente}", PLACA_INEXISTENTE, "1234598765")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se encuentra la moto con la placa ingresada"));;
    }

}
