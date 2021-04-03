package com.example.universe.controllers;

import com.example.universe.entities.Lord;
import com.example.universe.entities.Planet;
import com.example.universe.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService service;

    public PlanetController(PlanetService service) {
        this.service = service;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> savePlanet(@RequestBody Planet planet) {
        if (planet == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        planet = service.save(planet);
        return new ResponseEntity<>(planet, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> destroyPlanet(@PathVariable("id") Long planetId){
        service.terminate(planetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

   /* @DeleteMapping("/{planet_id}")
    public void destroyPlanet(@PathVariable(value = "planet_id") Long id){
        service.terminate(id);
    }*/
}
