package com.ayahathout.spring_security_with_jwt.repositories;

import com.ayahathout.spring_security_with_jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserDetails> findByEmail(String email);
}
