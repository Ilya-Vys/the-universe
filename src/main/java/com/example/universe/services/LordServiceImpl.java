package com.example.universe.services;

import com.example.universe.entities.Lord;
import com.example.universe.entities.Planet;
import com.example.universe.repositories.LordRepository;
import com.example.universe.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LordServiceImpl implements LordService{

    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;

    public LordServiceImpl(LordRepository lordRepository, PlanetRepository planetRepository) {
        this.lordRepository = lordRepository;
        this.planetRepository = planetRepository;
    }

    public Lord save(Lord lord){
        return lordRepository.save(lord);
    }

    public List<Lord> getLordWithoutPlanet(){
        return lordRepository.getAllByPlanetsIsNull();
    }


    public List<Lord> getYoungestLords(){
        return lordRepository.findTop10ByOrderByAge();
    }

    public void assignPlanetOwner(Long planetId, Long lordId){
        Planet planet = planetRepository.getOne(planetId);
        Lord lord = lordRepository.getOne(lordId);
        System.out.println(planet);
        System.out.println(lord);
        lord.addPlanet(planet);
        planet.setLord(lord);
        planetRepository.save(planet);
    }
}
