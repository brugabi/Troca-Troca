package com.uneb.spring_api;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    CommandLineRunner init (){
        return args -> {
            Files.createDirectories(Paths.get(uploadDir));
        };
    }

    
}
