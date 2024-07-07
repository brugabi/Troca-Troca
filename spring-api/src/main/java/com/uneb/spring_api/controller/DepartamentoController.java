package com.uneb.spring_api.controller;

import com.uneb.spring_api.dto.DepartamentoDTO;
import com.uneb.spring_api.models.Departamento;
import com.uneb.spring_api.service.DepartamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("/criar")
    public ResponseEntity<Map<String, Object>> criarDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento();
        Map<String, Object> response = new HashMap<>();
        departamento.setNome(departamentoDTO.nome());
        Departamento departamentoCriado = departamentoService.criarDepartamento(departamento);
        response.put("timestamp", LocalDateTime.now());
        response.put("message","Departamento criado com sucesso!!");
        response.put("data",departamentoCriado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
