package com.example.springsecurityimplementation.controller;

import com.example.springsecurityimplementation.entity.Student;
import com.example.springsecurityimplementation.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class StudentController {
    Logger log = LoggerFactory.getLogger(StudentController.class);
    //    @Autowired
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/student")
    public String getStudent() {
        log.info("####### The Work begin from here ######");
        return "Student";
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        log.info("####### The Work begin from here ######");
        return this.service.getAllStudent();
    }

    @GetMapping("/current_user")
    public String getLogInUser(Principal principal) {
       // log.info("### Current Login User is :#### " + principal.getName());
        StringBuffer sb= new StringBuffer();
        sb.append(principal.getName());
        log.info("### Current Login User is :#### "+sb );

        return principal.getName();
    }

}
