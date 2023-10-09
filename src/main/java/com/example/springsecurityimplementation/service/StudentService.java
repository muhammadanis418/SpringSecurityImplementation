package com.example.springsecurityimplementation.service;

import com.example.springsecurityimplementation.entity.Student;
import com.example.springsecurityimplementation.repository.StudentRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements UserDetailsService {

//    private List<Student> store=new ArrayList<>();
//    private String ids1=(UUID.randomUUID().toString());
//    private String ids2=(UUID.randomUUID().toString());
//    private String ids3=(UUID.randomUUID().toString());
//    private int hashIds1=ids1.hashCode();
//    private int hashIds2=ids2.hashCode();
//    private int hashIds3=ids3.hashCode();

    private StudentRepo repo;

    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    //    public StudentService() {
//        store.add(new Student(hashIds1,"Anis","anis@gamail.com"));
//        store.add(new Student(hashIds2,"Haroon","haroon@gmail.com"));
//        store.add(new Student(hashIds3,"Shehroz","shehroz@gmail.com"));
//    }
    public List<Student>getAllStudent(){
        return this.repo.findAll();
    }

    @Override
    public Student loadUserByUsername(String username) throws UsernameNotFoundException {
     // Optional<Student> std=
              return this.repo.findStudentByName(username).orElseThrow(()->new RuntimeException("User not found for "+username));
//        Optional<Student> std=this.repo.findStudentByName(username);
//            if(std.isPresent()){
//                throw new UsernameNotFoundException("Username Already exist with this name"+username);
//            }
//          else {
//                Student student= new Student();
//                student.setName(username);
//                this.repo.save(student);
//                return student;
//            }

    }


    public Student createStudent(Student student){
        Optional<Student>std= this.repo.findStudentByName(student.getName());
        if(std.isPresent()){
           throw new UsernameNotFoundException("Username Already exist with this name::: "+student.getUsername()) ;
        }
        else {
            student.setId(UUID.randomUUID().toString().hashCode());
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            return repo.save(student);
        }
    }


}
