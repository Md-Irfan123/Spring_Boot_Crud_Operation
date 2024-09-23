package com.jpa.spring_boot_crud_operation.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.jpa.spring_boot_crud_operation.response.UserError;

@RestControllerAdvice
public class UserExceptionHandler {
	
	
	
	

	
	  @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
		
		 Map<String, Object> errorResponse =  new HashMap<>();
	        errorResponse.put("timestamp", LocalDateTime.now());
	        errorResponse.put("message", ex.getMessage());
	        errorResponse.put("errorCode", HttpStatus.NOT_FOUND.value());
	

	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		
		
	}
	  
	  
	  


}
