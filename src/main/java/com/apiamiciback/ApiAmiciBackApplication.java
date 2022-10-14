package com.apiamiciback;

import com.apiamiciback.service.FileStorageService;
import com.apiamiciback.util.GeneratePassword;
import com.apiamiciback.util.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;


/**
 * The type Api amici back application.
 */
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApiAmiciBackApplication implements CommandLineRunner {


    @Autowired
    MailSenderService mailSenderService;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiAmiciBackApplication.class, args);
    }

    /**
     * The Storage service.
     */
    @Resource
    FileStorageService storageService;


    @Override
    public void run(String... args) throws Exception {
        //storageService.deleteAll();
        //storageService.init();
        log.info("API READY !!!!!!");
        // to send mail for prod
       // mailSenderService.sendEmail("kvanconingsloo@gmail.com", "Api ready !!", "Api run Sucessfully ");

    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Simple cors filter filter registration bean.
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8082"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    GeneratePassword generatePassword(){
        return new GeneratePassword();
    }


}
