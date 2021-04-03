package com.example.universe.repositories;

import com.example.universe.entities.Lord;
import com.example.universe.entities.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlanetRepositoryTest {

    @Autowired
    PlanetRepository repository;

    @Test
    void shouldSavePlanet() {
        Planet planet = new Planet("Planet100");
        repository.save(planet);
        assertThat(repository.findAll(), hasItem(planet));
    }

    @Sql("/sql/planets/shouldDeletePlanet.sql")
    @Test
    void shouldDeletePlanet(){
        Planet planet = repository.getOne(100L);
        repository.delete(planet);
        assertThat(repository.findAll(),not(hasItem(planet)));
    }

    @Sql("/sql/planets/shouldGetPlanet.sql")
    @Test
    void shouldGetPlanet(){
        Planet expected = new Planet("Planet333");
        Planet actual = repository.getOne(333L);
        assertEquals(expected.getName(), actual.getName());

    }

    @Sql("/sql/planets/shouldGetAllPlanet.sql")
    @Test
    void shouldGetAllPlanet(){
        Planet planet60 = new Planet("Planet60");
        Planet planet70 = new Planet("Planet70");
        Planet planet80 = new Planet("Planet80");
        List<Planet> actual = repository.findAll();
        assertThat(actual, hasItems(planet60, planet70, planet80));

    }

    @Sql("/sql/planets/shouldUpdatePlanet.sql")
    @Test
    void shouldUpdatePlanet(){
        Planet planet = repository.getOne(222L);
        Lord lord = new Lord("Lord", 100);
        planet.setLord(lord);
        repository.save(planet);
        Planet actual = repository.getOne(222L);
        assertEquals(lord, actual.getLord());


    }
}