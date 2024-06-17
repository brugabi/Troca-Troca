package com.uneb.spring_api.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.uneb.spring_api.dtos.UserRecordDto;
import com.uneb.spring_api.models.UserModel;
import com.uneb.spring_api.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;





@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setDataDeCadastro(LocalDateTime.now()); // Inserindo a data de criação do usuário
        try {
            userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created with success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userList = userRepository.findAll();
        if(!userList.isEmpty()) {
            for (UserModel userModel : userList) {
                UUID id = userModel.getId();
                userModel.add(linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
    
    @GetMapping("/api/user/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        userO.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("User List"));
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }
    
    @PutMapping("/api/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        var userModel = userO.get();
        BeanUtils.copyProperties(userRecordDto, userModel, "id", "dataDeCadastro");
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        userRepository.delete(userOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
