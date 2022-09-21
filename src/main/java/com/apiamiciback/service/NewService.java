package com.apiamiciback.service;

import com.apiamiciback.model.News;
import com.apiamiciback.repository.NewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    public List<News> getAllNews(){

        log.info("Get All News in database");
        return newRepository.findAll();
    }

    public News saveNews(News news){
        log.info("Saving news {} in database", news.getTitle());
        return newRepository.save(news);
    }
}