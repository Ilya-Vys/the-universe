package com.example.universe.services;

import com.example.universe.entities.Planet;
import com.example.universe.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService{

    private final PlanetRepository planetRepository;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet save(Planet planet){
        return  planetRepository.save(planet);
    }

    public void terminate(Long id){
        planetRepository.deleteById(id);
    }
}
