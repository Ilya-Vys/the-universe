package com.example.universe.services;

import com.example.universe.entities.Planet;

public interface PlanetService {


    Planet save(Planet planet);

    void terminate(Long id);

}
