package com.apiamiciback.service;

import com.apiamiciback.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
