package com.jpa.spring_boot_crud_operation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.jpa.spring_boot_crud_operation.response.APIResponse;
import com.jpa.spring_boot_crud_operation.service.UserService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	

	
	  @PostMapping(value = "/saveUserData") public ResponseEntity<?>
	  saveUserController(@Valid @RequestBody User user, BindingResult result) {

	  if(result.hasErrors()) { Map<String, String> errors=new
	  HashMap<String,String>();
	  
	  for(FieldError error:result.getFieldErrors()) {
	 
	  errors.put(error.getField(), error.getDefaultMessage()); 
	 } 
	  return new
	  ResponseEntity<>(errors,HttpStatus.BAD_REQUEST); 
	} 
	  User user1=new User();
	  
	  user1.setName(user.getName());
	  user1.setAddress(user.getAddress());
	  user1.setAge(user.getAge());
	  user1.setEmail(user.getEmail());
	  
	  service.saveUser(user1);
	 
	  return new
	  ResponseEntity<>("user data is valid & saved successfully",HttpStatus.OK); }
	 
	

	@GetMapping(value="/getAllUserData")
	public ResponseEntity<List<User>> getAllUserController() {

		return ResponseEntity.ok(service.findAllUserService());

	}

	@GetMapping("/getUserById/{id}")
	public User findByIdController(@PathVariable("id") Integer id){
		
		

		  return service.getByIdService(id);
					
	}
	
	@DeleteMapping(value = "/deleteUserById/{id}")
	public void deleteByIdController(@PathVariable("id") Integer id) {
		
		
		 service.deleteByIdService(id);
	
		
	}
	
	

    @PutMapping("/{id}")
    public User updateUserDataController(@PathVariable("id") Integer id,  @RequestBody User user) {
       
           return service.updateUserDataService(id, user);
       
    }
    
    @GetMapping(value="/{field}")
    public APIResponse<List<User>> findUserDataWithSortingController(@PathVariable String field){
    	
    	List<User> allUser=service.findUserDataWithSortingService(field);
    	
    	return new APIResponse<>(allUser.size(),allUser);
    	
    	
    }
    
    
    @GetMapping(value="/Pagination/{offset}/{pageSize}")
    public APIResponse<Page<User>> findProductWithPagination(@PathVariable int offset, @PathVariable int pageSize){
    	
	Page<User> allUser=service.findProductWithPagination(offset, pageSize);
    	
    	return new APIResponse<>(allUser.getSize(),allUser);
    	
    	
    	
    }
    
    



}
