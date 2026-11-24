package com.example.itemmanagement.Repository;

import com.example.itemmanagement.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token); // findByToken is a method when the frontend sends the token , it checks whether matching refresh-token was exists in the database table
}
