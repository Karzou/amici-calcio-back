package com.apiamiciback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig {

    /**
     * Cors configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
// Obliger de faire ceci pour débloquer la sécurité cors de l api appelant
  /*  @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                System.out.println("...Loading WebConfig...");
                registry.addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }
*/
}