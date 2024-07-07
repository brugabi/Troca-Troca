package com.uneb.spring_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FotoService {

    private final Path rootLocation;

    public FotoService() {
        this.rootLocation = Paths.get("src/main/resources/uploads");
    }

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!", e);
        }
    }

    public String saveFile(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile);
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
