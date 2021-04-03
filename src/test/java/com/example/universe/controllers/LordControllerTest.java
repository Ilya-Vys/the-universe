package com.example.universe.controllers;

import com.example.universe.utils.Converter;
import com.example.universe.entities.Lord;
import com.example.universe.entities.Planet;
import com.example.universe.services.LordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LordController.class)
class LordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LordServiceImpl service;

    @Test
    void shouldSaveLord() throws Exception {
        Lord lord = new Lord(77L, "Lord77", 77);
        given(service.save(any())).willReturn(lord);
        mockMvc.perform(post("/lords/")
                .content("{\"name\": \"Lord77\", \"age\": \"77\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void shouldGetLordWithoutPlanet() throws Exception {
        List<Lord> list = Arrays.asList(
                new Lord(555L, "Lord555", 555),
                new Lord(556L, "Lord556", 556),
                new Lord(557L, "Lord557", 557));
        when(service.getLordWithoutPlanet()).thenReturn(list);
        mockMvc.perform(get("/lords/getLordWithoutPlanets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(content().json(Converter.toJSON(list)));

    }

    @Test
    void shouldGetYoungestLords() throws Exception {
        List<Lord> list = new ArrayList<>();
        for (int i = 30; i < 40; i++) {
            Lord lord = new Lord((long)i , "Lord" + i, i);
            lord.addPlanet(new Planet((long)i, "Planet" + i));
            list.add(lord);
        }
        when(service.getYoungestLords()).thenReturn(list);
        mockMvc.perform(get("/lords/getYoungest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(10)))
                .andExpect(content().json(Converter.toJSON(list)));
    }

    @Test
    void shouldPlanetOwnership() throws Exception {
        doNothing().when(service).assignPlanetOwner(anyLong(), anyLong());
        mockMvc.perform(put("/lords/ownership")
                .param("planet_id", "77")
                .param("lord_id", "77")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}