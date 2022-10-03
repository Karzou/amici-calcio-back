package com.apiamiciback.controller;

import com.apiamiciback.model.News;
import com.apiamiciback.service.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping(value = "/getNews/{id}", consumes = {"application/json"})
    public ResponseEntity<News> getNews(@PathVariable int id){

        log.info("Call get news id : ",id);
        return ResponseEntity.ok().body(newService.getNew(id));
    }

    @PostMapping(value = "/saveNews",consumes = {"application/json"})
    public ResponseEntity<News>saveNews(@RequestBody @Valid News news){
        log.info("Call save news for : {}.", news.getTitle());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/news/saveNews").toUriString());
        return ResponseEntity.created(uri).body(newService.saveNews(news));
    }


    @PutMapping(value = "/updateNews/{id}",consumes = {"application/json"})
    public ResponseEntity<News>updateNews(@RequestBody  @Valid News news, @PathVariable int id){

        log.info("Call update news for : {}", news.getTitle());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/news/updateNews").toUriString());
        return ResponseEntity.created(uri).body(newService.updateNews(news, id));
    }
}
