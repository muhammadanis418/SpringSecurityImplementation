package com.example.springsecurityimplementation.controller;


import com.example.springsecurityimplementation.entity.Student;
import com.example.springsecurityimplementation.model.JWTRequest;
import com.example.springsecurityimplementation.model.JWTResponse;
import com.example.springsecurityimplementation.security.JwtHelper;
import com.example.springsecurityimplementation.service.StudentService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
//@Slf4j
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper helper;

    private StudentService service;


    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper helper, StudentService service) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.helper = helper;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getName(), jwtRequest.getPassword());

        UserDetails userdetails = userDetailsService.loadUserByUsername(jwtRequest.getName());
        String token = this.helper.generateToken(userdetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userdetails.getUsername()).build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void doAuthenticate(String name, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password);
        try {
            this.authenticationManager.authenticate(auth);
        } catch (BadCredentialsException bd) {
            throw new BadCredentialsException("Invalid Username & password");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid";
    }

    @PostMapping("/create-student")
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

//    @GetMapping("/all-student")
//    public List<Student> getAllStudent() {
//        return service.getAllStudent();
//    }

}
