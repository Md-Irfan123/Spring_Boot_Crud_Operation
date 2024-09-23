package com.jpa.spring_boot_crud_operation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.spring_boot_crud_operation.dto.User;
import com.jpa.spring_boot_crud_operation.exception.UserNotFoundException;
import com.jpa.spring_boot_crud_operation.repository.UserRepository;
import com.jpa.spring_boot_crud_operation.response.APIResponse;
import com.jpa.spring_boot_crud_operation.service.UserService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService service;  // Your service layer to handle business logic

    @Autowired
    private PasswordEncoder passwordEncoder;
	

	
	 

	    @PostMapping(value = "/saveUserData")
	    public ResponseEntity<?> saveUserController(@Valid @RequestBody User user, BindingResult result) {

	        // Handle validation errors
	        if (result.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            for (FieldError error : result.getFieldErrors()) {
	                errors.put(error.getField(), error.getDefaultMessage());
	            }
	            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	        }

	        // Create a new User entity and set the fields
	        User newUser = new User();
	        newUser.setUsername(user.getUsername());
	        newUser.setAddress(user.getAddress());
	        newUser.setAge(user.getAge());
	        newUser.setEmail(user.getEmail());
	        // Set password and role
	        newUser.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode the password
	        newUser.setRole(user.getRole().toUpperCase());  // Ensure role is uppercase (ADMIN, USER)
	 

	        // Save the user to the database
	        service.createUserData(newUser);

	        return new ResponseEntity<>("User data is valid & saved successfully", HttpStatus.OK);
	    }
    
		    

	
	
	

	 
	

	@GetMapping(value="/getAllUserData")
	public ResponseEntity<List<User>> getAllUserController() {

		return ResponseEntity.ok(service.findAllUsers());

	}

	@GetMapping("/getUserById/{id}")
	public User findByIdController(@PathVariable("id") Integer id){
		
		

		  return service.getById(id);
					
	}
	
	@DeleteMapping(value = "/deleteUserById/{id}")
	public void deleteByIdController(@PathVariable("id") Integer id) {
		
		
		 service.deleteById(id);
	
		
	}
	
	

    @PutMapping("/{id}")
    public User updateUserDataController(@PathVariable("id") Integer id,  @RequestBody User user) {
       
           return service.updateUserData(id, user);
       
    }
    
    @GetMapping(value="/{field}")
    public APIResponse<List<User>> findUserDataWithSortingController(@PathVariable String field){
    	
    	List<User> allUser=service.findUsersWithSorting(field);
    	
    	return new APIResponse<>(allUser.size(),allUser);
    	
    	
    }
    
    
    @GetMapping(value="/Pagination/{offset}/{pageSize}")
    public APIResponse<Page<User>> findProductWithPagination(@PathVariable int offset, @PathVariable int pageSize){
    	
	Page<User> allUser=service.findUsersWithPagination(offset, pageSize);
    	
    	return new APIResponse<>(allUser.getSize(),allUser);
    	
    	
    	
    }
    
    



}
