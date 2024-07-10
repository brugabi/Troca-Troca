package com.uneb.spring_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileStorageService {
    //coloca esse valor que esta na application.properties na uploadDir
    @Value("${file.upload-dir}")
    private String uploadDir;

    //servico para salvar a img na pasta uploads
    public String storeFile(MultipartFile file) throws IOException {
        //define o url final da img
        Path filePath = Paths.get(uploadDir).resolve(file.getOriginalFilename());
        //copia o conteudo da img para o destino especificado
        Files.copy(file.getInputStream(), filePath);
        //retorna o caminho da img
        return filePath.toString();
    }


}
