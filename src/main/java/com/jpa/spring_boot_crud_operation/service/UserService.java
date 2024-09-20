package com.jpa.spring_boot_crud_operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpa.spring_boot_crud_operation.dto.User;
import com.jpa.spring_boot_crud_operation.exception.UserNotFoundException;
import com.jpa.spring_boot_crud_operation.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	 private UserRepository repo;
	
	
	public User saveUser(User user) {
		
	return 	repo.save(user);
	}
	
	
	public List<User> findAllUserService(){
		return repo.findAll();
	}
	
	
	public User getByIdService(Integer id) throws UserNotFoundException {
	
		
		return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));
	}
	
	public void deleteByIdService(Integer id){
		repo.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));
	}
	


	public User updateUserDataService(Integer id, User user) throws UserNotFoundException  {
	    
		User userToUpdate=repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Entity not found with id " + id));

	        // Update fields of userToUpdate with new data from the user parameter
	        userToUpdate.setName(user.getName());
	        userToUpdate.setEmail(user.getEmail());
	        userToUpdate.setAge(user.getAge());
	        userToUpdate.setAddress(user.getAddress());
	        
	        User savedUser = repo.save(user);
	        return savedUser;
	        
	   
	}
			// For Sorting 
	
	public List<User> findUserDataWithSortingService(String field) {
	    return repo.findAll(Sort.by(Sort.Direction.ASC, field));
	
	}
	
			// For Pagination
	
	public Page<User> findProductWithPagination(int offset, int pageSize){
		
		Page<User> user=repo.findAll(PageRequest.of(offset, pageSize));
		return user;
		
	}


}
