package com.test.latam.controllers;


import com.test.latam.domain.response.PoemaRS;
import com.test.latam.services.PoemaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PoemaController.class})
@WebMvcTest
public class TestPoemaController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PoemaService poemaService;

    @Test
    public void testPoema() throws Exception {
        when(poemaService.obtenerPoema(anyString(),anyString(),anyString())).thenReturn(new PoemaRS());
        mockMvc.perform(get("/poema?nombres=Pablo Alejandro&apellidos=Silva Aravena&fechaNacimiento=10-04-1990")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();
    }
}
