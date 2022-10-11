package com.apiamiciback.service;

import com.apiamiciback.model.City;
import com.apiamiciback.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type City service.
 */
@Service
@Transactional
@Slf4j
public class CityService {

    /**
     * The City repository.
     */
    @Autowired
    CityRepository cityRepository;


    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public City getCityByCodePostal(int codePostal) {
        return cityRepository.findByCodePostal(codePostal);
    }
}
