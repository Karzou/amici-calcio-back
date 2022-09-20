package com.apiamiciback.service;

import com.apiamiciback.repository.NewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class NewService {

    @Autowired
    NewRepository newRepository;
}
