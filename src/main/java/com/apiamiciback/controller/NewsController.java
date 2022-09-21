package com.apiamiciback.controller;

import com.apiamiciback.model.News;
import com.apiamiciback.service.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewService newService;

    @GetMapping("/getAllNews")
    public ResponseEntity<List<News>> getAllNews(){

        log.info("Call get all news.");
        return ResponseEntity.ok().body(newService.getAllNews());
    }

    @PostMapping("/saveNews")
    public ResponseEntity<News>saveNews(News news){
        log.info("Call save news.");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/news/saveNews").toUriString());
        return ResponseEntity.created(uri).body(newService.saveNews(news));


    }
}
