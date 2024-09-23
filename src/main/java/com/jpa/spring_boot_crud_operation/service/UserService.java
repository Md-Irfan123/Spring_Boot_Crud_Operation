package com.jpa.spring_boot_crud_operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jpa.spring_boot_crud_operation.dto.User;
import com.jpa.spring_boot_crud_operation.exception.UserNotFoundException;
import com.jpa.spring_boot_crud_operation.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService  {

    @Autowired
    private UserRepository repo; // Repository for interacting with the database
    


    
    public User createUserData(User user) {
    	return repo.save(user);
    }

    public List<User> findAllUsers() {
        return repo.findAll();
    }

    public User getById(Integer id) throws UserNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));
    }

    public void deleteById(Integer id) throws UserNotFoundException {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));
        repo.delete(user); // Delete the user
    }

    public User updateUserData(Integer id, User user) throws UserNotFoundException {
        User userToUpdate = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));

        // Update fields of userToUpdate with new data from the user parameter
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setAge(user.getAge());
       userToUpdate.setAddress(user.getAddress());
       userToUpdate.setPassword(user.getPassword());
       userToUpdate.setRole(user.getRole());
        

        return repo.save(userToUpdate); // Save the updated user
    }

    // For Sorting 
    public List<User> findUsersWithSorting(String field) {
        return repo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // For Pagination
    public Page<User> findUsersWithPagination(int offset, int pageSize) {
        return repo.findAll(PageRequest.of(offset, pageSize));
    }
}
