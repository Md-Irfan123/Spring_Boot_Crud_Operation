package com.jpa.spring_boot_crud_operation.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    @NotNull(message = "Name is required")
	    private String username;
	    
	    @NotNull(message = "Address is required")
	    private String address;
	    
	    @Min(value = 18, message = "Age must be at least 18")
	    private int age;
	    
	    @Email(message = "Email should be valid")
	    private String email;
	    
	    @NotNull(message = "Password is required")
	    @Size(min = 8)
	    private String password;
	    
	    @NotNull(message = "Role is required")
	    private String role;  // This field is for assigning the role

}
