package com.apiamiciback.repository;

import com.apiamiciback.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface City repository.
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByCodePostal(int codePostal);
}
