package com.jpa.spring_boot_crud_operation.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatchers;

import com.jpa.spring_boot_crud_operation.service.UserService;

@Configuration

public class UserConfig {
	
	
	
	

	 

	 	



	 	
	 	
	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }



	 
			 @Bean
			 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			     return http
			         .csrf(csrf -> csrf.disable())            // Disable CSRF protection
			         .authorizeHttpRequests(authorize -> 
			             authorize.anyRequest().permitAll()   // Allow all requests without authentication
			         )
			         .httpBasic(Customizer.withDefaults())    // Enable HTTP Basic Authentication (though not enforced)
			         .build();                                // Build the SecurityFilterChain
	 }

	 



}
