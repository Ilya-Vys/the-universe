package com.example.universe.services;

import com.example.universe.entities.Lord;
import com.example.universe.entities.Planet;
import com.example.universe.repositories.LordRepository;
import com.example.universe.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
class LordServiceImplTest {

    @Mock
    private LordRepository repository;

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    LordServiceImpl service;

    @Test
    void shouldSave() {
        Lord lord = new Lord("Lord", 50);
        Lord savedLord = new Lord(50L, "Lord", 50);
        when(repository.save(lord)).thenReturn(savedLord);
        assertEquals(savedLord, service.save(lord));
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldGetLordWithoutPlanet() {
        List<Lord> lords = Arrays.asList(
                new Lord("Lord111", 111),
                new Lord("Lord112", 112),
                new Lord("Lord113", 113));
        when(repository.getAllByPlanetsIsNull()).thenReturn(lords);
        List<Lord> actual = repository.getAllByPlanetsIsNull();
        assertEquals(3, actual.size());
        verify(repository, times(1)).getAllByPlanetsIsNull();

    }


    @Test
    void shouldAssignPlanetOwner() {
        Lord lord = new Lord(44L,"Lord44", 44);
        Planet planet = new Planet(44L, "Planet44");
        when(repository.getOne(anyLong())).thenReturn(lord);
        when(planetRepository.getOne(anyLong())).thenReturn(planet);
        service.assignPlanetOwner(planet.getId(), lord.getId());
        assertEquals(planet.getLord(), lord);
        verify(planetRepository, times(1)).save(planet);

    }
}