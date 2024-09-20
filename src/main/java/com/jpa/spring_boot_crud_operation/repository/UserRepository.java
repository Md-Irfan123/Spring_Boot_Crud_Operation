package com.jpa.spring_boot_crud_operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.spring_boot_crud_operation.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
