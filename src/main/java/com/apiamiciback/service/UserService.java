package com.apiamiciback.service;

import com.apiamiciback.model.User;
import com.apiamiciback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;


    public String test() {

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");

        userRepository.save(user);
        log.error("Test : " + user.getFirstName());
        return "OK "+ user.getLastName() + " est créé.";
    }
}
