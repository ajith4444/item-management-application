package com.example.itemmanagement.Service;

import com.example.itemmanagement.Entity.RefreshToken;
import com.example.itemmanagement.Entity.User;
import com.example.itemmanagement.Repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken(); //Since the Refresh token is a java entity class, it is our responsible to create an object for this access to access its properties
        refreshToken.setToken(UUID.randomUUID().toString()); // we set the token with random UUID and convert that into string, since the token field declared as String
        refreshToken.setUser(user); // and assign the token to the specific user
        refreshToken.setExpiryDate(
                Instant.now().plus(7, ChronoUnit.DAYS)); // sets its expiry date to seven days from now.
        return refreshTokenRepository.save(refreshToken); // saves the token in MySQL using refreshTokenRepository.save() method
    }

    public RefreshToken validateRefreshToken(String token) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByToken(token);
        if (refreshTokenOptional.isEmpty()) { // Existence check
            throw new RuntimeException("Refresh token not found");
        }
        RefreshToken refreshToken = refreshTokenOptional.get();
        if (refreshToken.getExpiryDate().isBefore(Instant.now())) { // Expiry check
            throw new RuntimeException("Refresh token has expired");
        }
        return refreshToken; // return the object-which contains token,user,expiry date.-whoever calls this method, it returns the refreshToken entity to caller
    }
}
