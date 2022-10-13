package com.apiamiciback.repository;

import com.apiamiciback.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface City repository.
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * Find by code postal city.
     *
     * @param codePostal the code postal
     * @return the city
     */
    City findByCodePostal(int codePostal);
}
