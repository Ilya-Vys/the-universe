package com.example.universe.repositories;

import com.example.universe.entities.Lord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LordRepositoryTest {

    @Autowired
    LordRepository repository;

    @Autowired
    PlanetRepository planetRepository;

    @Test
    void shouldSaveLord() {
        Lord lord = new Lord("SavedLord", 10);
        repository.save(lord);
        assertThat(repository.findAll(), hasItem(lord));
    }

    @Sql("/sql/lords/shouldDeleteLord.sql")
    @Test
    void shouldDeleteLord(){
        Lord lord = repository.getOne(100L);
        repository.delete(lord);
        assertThat(repository.findAll(),not(hasItem(lord)));
    }

    @Sql("/sql/lords/shouldGetAllLords.sql")
    @Test
    void shouldGetAllAndAssertHavingLords() {
        Lord lord60 = new Lord("Lord60", 60);
        Lord lord70 = new Lord("Lord70", 70);
        Lord lord80 = new Lord("Lord80", 80);
        List<Lord> actual = repository.findAll();
        assertThat(actual, hasItems(lord60, lord70, lord80));
    }

    @Sql("/sql/lords/shouldGetLord.sql")
    @Test
    void shouldGetLord(){
        Lord expected = new Lord("Lord333", 333);
        Lord actual = repository.getOne(333L);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAge(), actual.getAge());
    }

    @Sql("/sql/lords/shouldGetTenTheYoungestLords.sql")
    @Test
    void shouldGetTenTheYoungestLords(){
        List<Lord> actual = repository.findTop10ByOrderByAge();
        List<Lord> expected = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            expected.add(new Lord("Lord" + i, i));
        }
        assertEquals(expected, actual);
    }

    @Sql("/sql/lords/shouldGetLordWithoutPlanet.sql")
    @Test
    void shouldGetLordWithoutPlanet(){
        Lord lord500 = new Lord("Lord500", 500);
        Lord lord501 = new Lord("Lord501", 501);
        Lord lord502 = new Lord("Lord502", 502);
        Lord lord503 = new Lord("Lord503", 503);
        Lord lord504 = new Lord("Lord504", 504);
        List<Lord> actual = repository.getAllByPlanetsIsNull();
        actual.forEach(System.out::println);
        assertThat(actual, hasItems(lord503, lord504));
        assertThat(actual, not(hasItems(lord500, lord501, lord502)));
    }


}