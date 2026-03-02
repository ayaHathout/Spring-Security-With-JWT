package com.ayahathout.spring_security_with_jwt.services;

import com.ayahathout.spring_security_with_jwt.configurations.JWTService;
import com.ayahathout.spring_security_with_jwt.dtos.AuthenticationResponseDTO;
import com.ayahathout.spring_security_with_jwt.dtos.RegisterationRequestDTO;
import com.ayahathout.spring_security_with_jwt.models.User;
import com.ayahathout.spring_security_with_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public AuthenticationResponseDTO register(RegisterationRequestDTO registerationRequestDTO) {
        User userToBeRegistered = User
                .builder()
                .email(registerationRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerationRequestDTO.getPassword()))
                .username(registerationRequestDTO.getUsername())
                .firstName(registerationRequestDTO.getFirstName())
                .lastName(registerationRequestDTO.getLastName())
                .build();

        userRepository.save(userToBeRegistered);

        String token = jwtService.generateToken(userToBeRegistered);
        return AuthenticationResponseDTO
                .builder()
                .token(token)
                .build();
    }
}
