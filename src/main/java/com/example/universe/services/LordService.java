package com.example.universe.services;

import com.example.universe.entities.Lord;
import java.util.List;

public interface LordService {


    Lord save(Lord lord);

    List<Lord> getLordWithoutPlanet();

    List<Lord> getYoungestLords();

    void assignPlanetOwner(Long planetId, Long lordId);

}
