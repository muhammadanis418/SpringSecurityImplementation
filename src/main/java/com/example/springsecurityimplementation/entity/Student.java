package com.example.springsecurityimplementation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Student {

    private int id;
    private String name;
    private String email;
}
