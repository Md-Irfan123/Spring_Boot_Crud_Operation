package com.jpa.spring_boot_crud_operation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
	
	
		@GetMapping("/home")
	    public String handleWelcome() {
	        return "home"; // Accessible to everyone
	    }

	    @GetMapping("/admin/home")
	    public String handleAdminHome() {
	        return "home_admin"; // Accessible only to ADMIN
	    }

	    @GetMapping("/user/home")
	    public String handleUserHome() {
	        return "home_user"; // Accessible only to USER
	    }			

}
