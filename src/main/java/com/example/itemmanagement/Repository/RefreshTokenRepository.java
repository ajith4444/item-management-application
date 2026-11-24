package com.example.itemmanagement.Repository;

import com.example.itemmanagement.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> { // This entire repository manages RefreshToken entities
    //conceptually spring data JPA generates a similar query - SELECT * FROM refresh_tokens where token = ?;
    Optional<RefreshToken> findByToken(String token); // findByToken is a method when the frontend sends the token , it checks whether matching refresh-token was exists in the database table
    //DELETE FROM refresh_tokens WHERE token = ? ; // similar query only
    void deleteByToken(String token); // here void is the return type , which this method does not return any value
}
