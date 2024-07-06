package com.uneb.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uneb.spring_api.models.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
