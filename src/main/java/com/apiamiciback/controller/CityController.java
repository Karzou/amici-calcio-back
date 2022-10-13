package com.apiamiciback.controller;

import com.apiamiciback.model.City;
import com.apiamiciback.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type City controller.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    /**
     * The City service.
     */
    @Autowired
    CityService cityService;

    /**
     * Get all cities response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getAllCities")
    public ResponseEntity<List<City>> getAllCities(){

        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }
}
