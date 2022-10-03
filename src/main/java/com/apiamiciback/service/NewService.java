package com.apiamiciback.service;

import com.apiamiciback.exception.NotFoundException;
import com.apiamiciback.model.News;
import com.apiamiciback.repository.NewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
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

    public News getNew(int id){
        log.info("Get news : {}", id);
        return newRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("New not found in DataBase : " + id));
    }
    public News saveNews(News news){
        log.info("Saving news {} in database", news.getTitle());
        return newRepository.save(news);
    }

    public News updateNews(News newsRequest, int id){
        log.info("Call update news service for : {}", newsRequest.getTitle());
        News news = newRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("New not found in DataBase : " + id + newsRequest.getTitle()));

        news.setText(newsRequest.getText());
        news.setTitle(newsRequest.getTitle());
        news.setLastUpdate(new Date(System.currentTimeMillis()));

        log.info("News {} updated in database.", news.getTitle());
        return newRepository.save(news);
    }
}
