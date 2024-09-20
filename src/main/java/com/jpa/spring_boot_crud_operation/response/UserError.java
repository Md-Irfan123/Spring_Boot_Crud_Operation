package com.jpa.spring_boot_crud_operation.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserError {
	
	private String errorCode;
	private String message;
	private HttpStatus httpStatus;

}
