package com.apiamiciback.service;

import com.apiamiciback.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Team user service.
 */
@Service
@Slf4j
@Transactional
public class TeamUserService {

    /**
     * The Team repository.
     */
    @Autowired
    TeamRepository teamRepository;

}
