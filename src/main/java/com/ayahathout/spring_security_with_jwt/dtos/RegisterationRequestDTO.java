package com.ayahathout.spring_security_with_jwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterationRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
}
