package com.apiamiciback.service;

import com.apiamiciback.repository.NewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type New service.
 */
@Service
@Transactional
@Slf4j
public class NewService {

    /**
     * The New repository.
     */
    @Autowired
    NewRepository newRepository;
}
