package com.jpa.spring_boot_crud_operation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.spring_boot_crud_operation.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Method to find a user by username
    Optional<User> findByUsername(String username);
//    
//    // Method to find a user by email (optional, but often useful)
////    Optional<User> findByEmail(String email);
//    
//    // You can add more custom query methods as needed
}
