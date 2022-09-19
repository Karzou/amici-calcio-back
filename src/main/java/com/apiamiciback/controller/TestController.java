package com.apiamiciback.controller;


import com.apiamiciback.model.User;
import com.apiamiciback.service.RoleService;
import com.apiamiciback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestController {


    final private UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String test(){
        log.info("Passage dans test get ");


        User user = new User();
        user.setEmail("Test");
        user.setPassword("1234");
        user.setLastName("Admin");
        user.setFirstName("Test");
        user.setRole(roleService.getRole(1));
        try{
            userService.saveUser(user);
            log.info("User créé en database {}", user.getEmail());
        }catch(Exception e){
            log.error("Erreur lors de l intégration en db de {}", user.getEmail());
        }
        //log.info("Test user : {}, {}", user.get().getIdUser(), user.get().getRole().getDescription());
        //String message = userService.test();
        return "Test Ok !" ;
    }
}
