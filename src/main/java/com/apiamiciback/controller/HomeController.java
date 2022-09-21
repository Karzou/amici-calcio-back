package com.apiamiciback.controller;

import com.apiamiciback.model.News;
import com.apiamiciback.service.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @Autowired
    private NewService newService;


    @GetMapping
    public ResponseEntity<?> getHome() {

        log.info("Call get all news.");
        List<News> news = newService.getAllNews();
        log.info(" {}  news will be send to front.", news.size());
        return ResponseEntity.ok().body("OK");
    }
}
