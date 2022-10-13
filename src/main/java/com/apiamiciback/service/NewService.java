package com.apiamiciback.service;

import com.apiamiciback.dto.NewsRequestDto;
import com.apiamiciback.exception.NotFoundException;
import com.apiamiciback.model.News;
import com.apiamiciback.model.User;
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
        return newRepository.findAllByCreatedDesc();
    }

    public News getLastNew(){
        return newRepository.findAllByCreatedDesc().get(0);
    }

    public News getNew(int id){
        log.info("Get news : {}", id);
        return newRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("New not found in DataBase : " + id));
    }
    public News saveNews(NewsRequestDto newsRequestDto){
        log.info("Saving news {} in database", newsRequestDto.getTitle());

        News news = News.builder()
                .title(newsRequestDto.getTitle())
                .text(newsRequestDto.getContent())
                .createdAt(newsRequestDto.getCreatedAt())
                .imgUrl(newsRequestDto.getUrl())
                .build();

        return newRepository.save(news);
    }

    public News updateNews(News newsRequest, int id){
        log.info("Call update news service for : {}", newsRequest.getTitle() + " ");
        News news = newRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("New not found in DataBase : " + id + newsRequest.getTitle()));

        news.setText(newsRequest.getText());
        news.setTitle(newsRequest.getTitle());
        news.setLastUpdate(new Date(System.currentTimeMillis()));

        log.info("News {} updated in database.", news.getTitle());
        return newRepository.save(news);
    }

    public void deleteNews(int id){
        log.info("Service delete news id : {}", id);

        News news = newRepository.findById(id).orElseThrow();
        log.info("Delete user {}", news.getTitle());

        newRepository.delete(news);
    }
}
