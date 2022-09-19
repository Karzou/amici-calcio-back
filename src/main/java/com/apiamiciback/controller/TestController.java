package com.apiamiciback.controller;


import com.apiamiciback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String test(){
        log.info("Passage dans test get ");

        String message = userService.test();
        return "Test Ok !" + message;
    }
}
