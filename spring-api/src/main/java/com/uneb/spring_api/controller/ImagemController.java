package com.uneb.spring_api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uneb.spring_api.models.Imagem;
import com.uneb.spring_api.repositories.ImagemRepository;
import com.uneb.spring_api.service.FileStorageService;

@RestController
@RequestMapping("/api/imagens")
public class ImagemController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImagem(@RequestParam("file") MultipartFile file){
        try {
            String filePath = fileStorageService.storeFile(file);
            Imagem imagem = new Imagem();
            imagem.setUrl(filePath);
            imagemRepository.save(imagem);
            return ResponseEntity.ok("Foooi" + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tristeza");
        }
    }
    
}
