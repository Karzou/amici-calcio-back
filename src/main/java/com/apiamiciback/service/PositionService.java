package com.apiamiciback.service;

import com.apiamiciback.model.Position;
import com.apiamiciback.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Position service.
 */
@Service
@Transactional
@Slf4j
public class PositionService {

    /**
     * The Position repository.
     */
    @Autowired
    PositionRepository positionRepository;

    /*public Position getPositionById(int id){
        Position position = positionRepository.findById(id);
        return position;
    }*/

    public Position getPositionByIf(int id) {
        Position position = positionRepository.findById(2).orElseThrow();

        return position;
    }
}
