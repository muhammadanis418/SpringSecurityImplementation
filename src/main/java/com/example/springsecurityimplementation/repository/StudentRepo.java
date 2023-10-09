package com.example.springsecurityimplementation.repository;

import com.example.springsecurityimplementation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

 Optional< Student> findStudentByName(String name);  //make it optional
}
