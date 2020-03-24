package com.linkshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NewsFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFeedApplication.class, args);
	}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/links").allowedOrigins("http://localhost:3000");
                registry.addMapping("/link/short").allowedOrigins("http://localhost:3000");
                registry.addMapping("/link/delete/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
