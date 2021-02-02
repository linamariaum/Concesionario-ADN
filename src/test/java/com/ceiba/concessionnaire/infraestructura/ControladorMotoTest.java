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
public class ControladorMotoTest {

    public static final String PLACA_YA_CREADA_1 = "ADB34D";
    public static final String PLACA_YA_CREADA_2 = "EFG56H";
    public static final String PLACA_CREAR = "DUH43B";
    public static final String PLACA_INEXISTENTE = "QWE89T";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void obtenerMotoPorPlaca() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/motos/{placa}", PLACA_YA_CREADA_2)
            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACA_YA_CREADA_2));
    }

    @Test
    public void obtenerMotoPorPlacaInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/motos/{placa}", PLACA_INEXISTENTE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se encuentra la moto con la placa ingresada"));
    }

    @Test
    public void obtenerMotos() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/motos")
            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void crearMoto() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().buildComando();
        mvc.perform(MockMvcRequestBuilders
            .post("/motos")
            .content(objectMapper.writeValueAsString(comandoMoto))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/motos/{placa}", PLACA_CREAR)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACA_CREAR));
    }

    @Test
    public void crearMotoRepetida() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().conPlaca(PLACA_YA_CREADA_1).buildComando();
        mvc.perform(MockMvcRequestBuilders
                .post("/motos")
                .content(objectMapper.writeValueAsString(comandoMoto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se pudo agregar la motocicleta."));
    }

    @Test
    public void crearMotoConPlacaInvalida() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().buildComandoConPlacaErronea();
        mvc.perform(MockMvcRequestBuilders
                .post("/motos")
                .content(objectMapper.writeValueAsString(comandoMoto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Placa inválida"));
    }

    @Test
    public void crearMotoConPrecioInvalido() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().buildComandoConPrecioErroneo();
        mvc.perform(MockMvcRequestBuilders
                .post("/motos")
                .content(objectMapper.writeValueAsString(comandoMoto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Precio inválido"));
    }

    @Test
    public void actualizarMoto() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().conPlaca(PLACA_YA_CREADA_2).conColor("MORADO").buildComando();
        mvc.perform(MockMvcRequestBuilders
                .put("/motos/{placa}", PLACA_YA_CREADA_2)
                .content(objectMapper.writeValueAsString(comandoMoto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACA_YA_CREADA_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("MORADO"));
    }

    @Test
    public void actualizarMotoInexistente() throws Exception {
        ComandoMoto comandoMoto = new MotoTestDataBuilder().buildComandoConPlacaInexistente();
        mvc.perform(MockMvcRequestBuilders
                .put("/motos/{placa}", PLACA_INEXISTENTE)
                .content(objectMapper.writeValueAsString(comandoMoto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("No se encuentra la moto con la placa ingresada"));
    }

}
