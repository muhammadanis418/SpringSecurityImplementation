package com.example.springsecurityimplementation.service;

import com.example.springsecurityimplementation.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private List<Student> store=new ArrayList<>();
    private String ids1=(UUID.randomUUID().toString());
    private String ids2=(UUID.randomUUID().toString());
    private String ids3=(UUID.randomUUID().toString());
    private int hashIds1=ids1.hashCode();
    private int hashIds2=ids2.hashCode();
    private int hashIds3=ids3.hashCode();

    public StudentService() {
        store.add(new Student(hashIds1,"Anis","anis@gamail.com"));
        store.add(new Student(hashIds2,"Haroon","haroon@gmail.com"));
        store.add(new Student(hashIds3,"Shehroz","shehroz@gmail.com"));
    }
    public List<Student>getAllStudent(){
      return this.store;
    }
}
