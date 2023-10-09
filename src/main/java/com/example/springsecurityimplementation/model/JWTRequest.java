package com.example.springsecurityimplementation.model;

import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//     @Builder
@ToString
public class JWTRequest {

    private String name;
    private String password;
}
