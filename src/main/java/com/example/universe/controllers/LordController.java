package com.example.universe.controllers;


import com.example.universe.entities.Lord;
import com.example.universe.services.LordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lords")
public class LordController {

    private final LordService service;

    public LordController(LordService service) {
        this.service = service;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lord> saveLord(@RequestBody Lord lord) {
        if (lord == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        lord = service.save(lord);
        return new ResponseEntity<>(lord, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getLordWithoutPlanets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lord>> getLordWithoutPlanet() {
        return new ResponseEntity<>(service.getLordWithoutPlanet(), HttpStatus.OK);
    }

    @GetMapping(value = "/getYoungest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lord>> getYoungestLords() {
        return new ResponseEntity<>(service.getYoungestLords(), HttpStatus.OK);
    }

    @PutMapping("/ownership")
    public ResponseEntity<Lord> planetOwnership(@RequestParam(value = "planet_id", required = false) Long planetId
            , @RequestParam(value = "lord_id") Long lordId) {
        service.assignPlanetOwner(planetId, lordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
