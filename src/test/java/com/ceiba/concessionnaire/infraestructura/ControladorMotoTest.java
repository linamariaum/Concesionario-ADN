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

    public static final String PLACA_0 = "DUH43B";
    public static final String PLACA_1 = "DUH43C";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void obtenerMotoPorPlaca() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/motos/{placa}", PLACA_1)
            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACA_1));
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
                .get("/motos/{placa}", PLACA_0)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACA_0));
    }

}
