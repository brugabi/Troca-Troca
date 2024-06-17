package com.uneb.spring_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uneb.spring_api.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel,UUID>{

}
