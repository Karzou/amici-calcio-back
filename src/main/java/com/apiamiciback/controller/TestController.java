package com.apiamiciback.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/")
    public String test(){
        log.info("Passage dans test get ");
        return "Test Ok !";
    }
}
