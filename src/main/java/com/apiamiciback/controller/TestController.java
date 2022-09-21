package com.apiamiciback.controller;


import com.apiamiciback.dto.UserRequestDto;
import com.apiamiciback.model.User;
import com.apiamiciback.service.RoleService;
import com.apiamiciback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The type Test controller.
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestController {


    final private UserService userService;

    /**
     * The Role service.
     */
    @Autowired
    RoleService roleService;

    /**
     * Test string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String test(){
        log.info("Passage dans test get ");


        User user = new User();
        user.setEmail("Date");
        user.setPassword("1234");
        user.setLastName("Date");
        user.setFirstName("Test");
        String date = "1984-10-31";
        try {
            user.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setRole(roleService.getRole(3));
        try{
//            userService.saveUser(user);
            log.info("User créé en database {}", user.getEmail());
        }catch(Exception e){
            log.error("Erreur lors de l intégration en db de {}", user.getEmail());
        }
        //log.info("Test user : {}, {}", user.get().getIdUser(), user.get().getRole().getDescription());
        //String message = userService.test();
        return "Test Ok !" ;
    }

    /**
     * Test permis string.
     *
     * @return the string
     */
    @GetMapping ("/permis")
    public String testPermis () {

        // recuperer l username connecté !!!!
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Test permis Ok " + username;
    }

    @PostMapping(value = "/validator", consumes = {"application/json"})
    public String testValidator(@RequestBody @Valid UserRequestDto user)  {



        userService.saveUser(user);


        return "Test validator OK !! " + user.getEmail();

    }


}
