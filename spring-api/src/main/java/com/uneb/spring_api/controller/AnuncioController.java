package com.uneb.spring_api.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uneb.spring_api.dto.AnuncioDTO;
import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.Departamento;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.AnuncioService;
import com.uneb.spring_api.service.DepartamentoService;
import com.uneb.spring_api.service.FileStorageService;

import com.uneb.spring_api.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired FileStorageService fileStorageService;

    


    @PostMapping("/criarAnuncio") // Rota usada para criar um anuncio
    public ResponseEntity<Map<String, Object>> createAnuncio(@Valid @RequestBody AnuncioDTO anuncioDTO) {
        Anuncio anuncio = new Anuncio(); // Intanciando um novo objeto anuncio
        Map<String,Object> response = new HashMap<>(); // Instanciando um objeto response para estruturar a saída
        response.put("datetime",LocalDateTime.now()); // timestamp
        Optional<User> criador = userService.verUsuario(anuncioDTO.idCriador()); // procurando o usuário baseado no id passado
        Optional<Departamento> departamento = departamentoService.verDepartamento(anuncioDTO.idDepartamento()); // procurando o deparatamento com o id passado

        
        if (criador.isPresent() && departamento.isPresent()) { // Verificando se ambos usuario e departamento existem
            // Dfinindo as propriedades do objeto que será salvo no banco
            anuncio.setTitulo(anuncioDTO.titulo());
            anuncio.setDescricao(anuncioDTO.descricao());
            anuncio.setCriador(criador.get());
            anuncio.setDepartamento(departamento.get());
            anuncio.setStatus(true);

            
            anuncioService.criarAnuncio(anuncio);
            response.put("anuncio",anuncio);
            return new ResponseEntity<>(response,HttpStatus.OK); // Retorna o anuncio
        } else {
            response.put("error","Ou o departamento ou o criador nao existe.");
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE); // Retorna um erro
        }
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadImagem(@RequestParam("file") MultipartFile file, @PathVariable Long id){
        try {
            String filePath = fileStorageService.storeFile(file);
            Optional<Anuncio> anuncio_upload = anuncioService.verAnuncio(id);
            Map<String,Object> response = new HashMap<>();
            if (anuncio_upload.isPresent()) {
                Anuncio anuncio = anuncio_upload.get();
                anuncio.setImagemUrl(filePath);

                anuncioService.atualizarStatusAnuncio(anuncio);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {
                response.put("error", "Anúncio não encontrado. Não foi possível salvar a imagem.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }     
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tristeza");
        }
    }

    @GetMapping("/busca") //
    public List<Anuncio> buscarAnuncios (@RequestParam String titulo) {
        return anuncioService.buscarPorTitulo(titulo);
    }

    @GetMapping("/{id}") // rota para buscar um anuncio pelo titulo
    public ResponseEntity<Map<String,Object>> verAnuncio(@PathVariable Long id) {
        Optional<Anuncio> anuncio = anuncioService.verAnuncio(id); // procurando as infomrações do anuncio baseado no id
        Map<String,Object> response = new HashMap<>();
        response.put("datetime",LocalDateTime.now());
        if (anuncio.isPresent()) { // verifica se o anuncio existe
            response.put("anuncio",anuncio.get());
            return new ResponseEntity<>(response,HttpStatus.OK); // retorna as informações do anuncio e status 200
        }else{
            response.put("Error","Anuncio nao encontrado.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND); // retonra erro
        }
    }

    @GetMapping("/listarAnuncios") // rota para listar todos os anuncios
    public ResponseEntity<Map<String, Object>> listarAnuncio() {
        Map<String,Object> response = new HashMap<>();
        response.put("Datetime", LocalDateTime.now());
        response.put("Lista",anuncioService.listarAnuncios());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/listar/categoria/{idCategoria}") // rota para listar todos os anuncios baseado na categoria
    public List<Anuncio> getMethodName(@PathVariable Long idCategoria) {
        System.out.println(idCategoria);
        return anuncioService.listarAnuncioByCategoryId(idCategoria);
    }
    

    @GetMapping("/listarAnuncios/{userId}") // rota para listar todos os anuncios baseado no usuario que criou
    public ResponseEntity<Map<String,Object>> getAnunciosByUserId(@PathVariable Long userId) {
        Optional<User> criador = userService.verUsuario(userId);
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        if (criador.isPresent()) {
            response.put("anuncios",anuncioService.getAnunciosByUser(criador.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("error","O id informado nao esta associado a nenhum usuario.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}") // rota para deletar um anuncio
    public ResponseEntity<Map<String, Object>> deletarAnuncio(@PathVariable Long id) {
        Optional<Anuncio> anuncio = anuncioService.verAnuncio(id);
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        if (anuncio.isPresent()) {
            anuncioService.deletarAnuncio(id);
            response.put("message","anuncio deletado com sucesso.");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("error","Anuncio nao encontrado. Nao foi efetuado o delete.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/alterarStatus/{id}") // rota para alterar o status
    public ResponseEntity<Map<String, Object>> alterarStatusAnuncio(@PathVariable Long id) {
    Optional<Anuncio> optionalAnuncio = anuncioService.verAnuncio(id);
    Map<String,Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());

    if (optionalAnuncio.isPresent()) {
        Anuncio anuncio = optionalAnuncio.get();
        anuncio.setStatus(false); // Altera o status para false

        anuncioService.atualizarStatusAnuncio(anuncio); // Atualiza apenas o status no banco de dados

        response.put("message", "Status do anúncio alterado para false.");
        response.put("anuncio", anuncio);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
        response.put("error", "Anúncio não encontrado. Não foi possível alterar o status.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
 
}
