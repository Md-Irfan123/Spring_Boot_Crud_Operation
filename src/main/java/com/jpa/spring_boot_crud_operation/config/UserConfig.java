package com.jpa.spring_boot_crud_operation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfig {
	
	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails admin = User.builder()
	                .username("Irfan")
	                .password(passwordEncoder().encode("1234"))
	                .roles("ADMIN")  // "Admin" is automatically mapped to "ROLE_Admin"
	                .build();

	        UserDetails user = User.builder()
	                .username("Arman")
	                .password(passwordEncoder().encode("1234"))
	                .roles("USER")  // "User" is automatically mapped to "ROLE_User"
	                .build();

	        return new InMemoryUserDetailsManager(admin, user);
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
	        return security.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(authorize -> authorize
	                		
	                    // Only ADMIN can perform GET, POST, DELETE, PUT operations
	                		
	                    .requestMatchers(HttpMethod.GET, "/user/getAllUserData", "/user/getUserById/{id}").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.POST, "/user/saveUserData").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.DELETE, "/user/deleteUserById/{id}").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("ADMIN")
	                    
	                    // Both ADMIN and USER can perform GET on these specific routes
	                    
	                    .requestMatchers(HttpMethod.GET, "/user/{field}", "/user/Pagination/{offset}/{pageSize}").hasAnyRole("ADMIN", "USER")
	                    
	                    .anyRequest().authenticated()
	                )
	                .httpBasic(Customizer.withDefaults())
	                .build();
	    } 

}
