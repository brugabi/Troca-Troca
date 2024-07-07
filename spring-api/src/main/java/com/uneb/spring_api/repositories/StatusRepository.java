package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
