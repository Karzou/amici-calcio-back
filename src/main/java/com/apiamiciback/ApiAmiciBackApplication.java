package com.apiamiciback;

import com.apiamiciback.model.Role;
import com.apiamiciback.model.User;
import com.apiamiciback.repository.RoleRepository;
import com.apiamiciback.service.RoleService;
import com.apiamiciback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApiAmiciBackApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ApiAmiciBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("API READY !!!!!!");

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
