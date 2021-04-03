package com.example.universe.controllers;

import com.example.universe.entities.Planet;
import com.example.universe.services.PlanetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanetController.class)
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlanetServiceImpl service;

    @Test
    void shouldSavePlanet() throws Exception {
        Planet planet = new Planet(77L, "Planet77");
        given(service.save(any())).willReturn(planet);
        mockMvc.perform(post("/planets/")
                .content("{\"name\": \"Planet77\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void shouldDestroyPlanet() throws Exception {
        doNothing().when(service).terminate(any());
        mockMvc.perform(delete("/planets/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}