package com.uneb.spring_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uneb.spring_api.models.Imagem;
import com.uneb.spring_api.repositories.ImagemRepository;

@Service
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    ImagemRepository imagemRepository;

    public String storeFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }

    public Optional<Imagem> obterImagem(Long id){
        return imagemRepository.findById(id);
    }
}
