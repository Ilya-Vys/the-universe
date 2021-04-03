package com.example.universe.services;

import com.example.universe.entities.Planet;
import com.example.universe.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
class PlanetServiceImplTest {

    @Mock
    private PlanetRepository repository;

    @InjectMocks
    PlanetServiceImpl service;

    @Test
    void shouldSave() {
        Planet planet = new Planet("Planet50");
        Planet savedPlanet = new Planet(50L, "Planet50");
        when(repository.save(planet)).thenReturn(savedPlanet);
        assertEquals(savedPlanet, service.save(planet));
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldTerminate() {
        repository.delete(any());
        verify(repository, times(1)).delete(any());
    }
}