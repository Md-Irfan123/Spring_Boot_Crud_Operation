                          SPRING BOOT CRUD OPERATION
        
 the full range of CRUD (Create, Read, Update, Delete) operations along with pagination and sorting in your Spring Boot project! Here’s a brief overview of how each of these operations can be managed in Spring Boot:

Create: You typically use POST requests to add new records to the database. In Spring Boot, this is handled using @PostMapping in your controller and saving the entity using a repository like JpaRepository.

Read (GetAllData): You can use GET requests to fetch records from the database. To get all records, you can use @GetMapping, with the repository’s findAll() method.

Update: For updating records, PUT requests are usually used. In Spring Boot, you might use @PutMapping in the controller and repository methods to update an existing entity.

Delete: You can handle deletion using @DeleteMapping with the repository’s deleteById() method or similar.

Pagination: Spring Data JPA makes pagination easy. The Pageable interface, combined with the repository’s findAll(Pageable pageable) method, enables pagination.

Sorting: Sorting can be implemented with the Sort class, which can be passed along with Pageable in the repository's method to handle both pagination and sorting.


   **   Steps for Handling Exceptions:**
      
Custom Exception Classes: You can create custom exception classes to represent specific errors in your application. For example, a UserNotFoundException for when a requested resource isn't found:

Global Exception Handler: Use the @ControllerAdvice and @ExceptionHandler annotations to create a centralized error-handling mechanism. This will ensure that all controllers 
in your application share a common response format when exceptions are thrown.


   UserConfig Class 

The UserConfig class is a Spring configuration class responsible for setting up security features in a Spring Boot application. It defines user roles, password encoding, and authorization rules for accessing specific endpoints.

@Configuration: Indicates that this class contains one or more bean methods, which will be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.


Certainly! Below is a sample documentation for your UserConfig class, explaining its purpose and components. This can be helpful for you or anyone else looking at the code later.

UserConfig Class Documentation
Overview
The UserConfig class is a Spring configuration class responsible for setting up security features in a Spring Boot application. It defines user roles, password encoding, and authorization rules for accessing specific endpoints.

Annotations
@Configuration: Indicates that this class contains one or more bean methods, which will be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

Methods

   1. passwordEncoder()

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
Description: Creates a PasswordEncoder bean using the BCrypt algorithm for hashing passwords.
Return Type: PasswordEncoder
Usage: This bean is used to encode passwords for user authentication.

   2. userDetailsService()

@Bean
public UserDetailsService userDetailsService() {
    UserDetails admin = User.builder()
            .username("Irfan")
            .password(passwordEncoder().encode("1234"))
            .roles("Admin")  // "Admin" is automatically mapped to "ROLE_Admin"
            .build();

    UserDetails user = User.builder()
            .username("Arman")
            .password(passwordEncoder().encode("1234"))
            .roles("User")  // "User" is automatically mapped to "ROLE_User"
            .build();

    return new InMemoryUserDetailsManager(admin, user);
}
Description: Creates an InMemoryUserDetailsManager containing two users: an admin and a regular user. The admin has the role "Admin", while the user has the role "User".
Return Type: UserDetailsService
Usage: This service is responsible for loading user-specific data for authentication and authorization.

       3. securityFilterChain(HttpSecurity security)

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
    return security.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/user/getAllUserData", "/user/getUserById/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/user/saveUserData").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/user/deleteUserById/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/user/{field}", "/user/Pagination/{offset}/{pageSize}").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
}
Description: Configures HTTP security settings for the application. It disables CSRF protection and sets up authorization rules for various endpoints based on user roles.
Parameters:
HttpSecurity security: Configures HTTP security.
Return Type: SecurityFilterChain
Usage: This method defines which roles can access specific HTTP methods on defined endpoints.

       Role Management
Admin Role: Users with the role "Admin" can perform all operations on user-related endpoints (GET, POST, DELETE, PUT).
User Role: Users with the role "User" can perform GET operations on specific endpoints.

