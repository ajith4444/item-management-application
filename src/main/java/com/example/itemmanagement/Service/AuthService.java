package com.example.itemmanagement.Service;

import com.example.itemmanagement.DTO.AuthResponseDTO;
import com.example.itemmanagement.DTO.RefreshTokenResponseDTO;
import com.example.itemmanagement.Entity.RefreshToken;
import com.example.itemmanagement.Entity.User;
import com.example.itemmanagement.Repository.RefreshTokenRepository;
import com.example.itemmanagement.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

//this service will handle the login logic
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository){
        this.authenticationManager= authenticationManager;
        this.jwtService= jwtService;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthResponseDTO login(String username, String password){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,password);
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        String authenticatedUsername = authentication.getName(); // retrieving the username from the authentication result
        User user = userRepository.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));// we find the authenticated username from the User entity Object, since RefreshToken service method expects a User object
        String accessToken = jwtService.generateToken(authenticatedUsername);// generates the token for the authenticated username
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return new AuthResponseDTO(accessToken,refreshToken.getToken());
    }

    public RefreshTokenResponseDTO refreshAccessToken(String token){
        RefreshToken refreshToken = refreshTokenService.validateRefreshToken(token); // obtained the successfully validated RefreshToken entity and stored in a refreshToken variable
        String username = refreshToken.getUser().getUsername();
        String newAccessToken = jwtService.generateToken(username);
        return new RefreshTokenResponseDTO(newAccessToken);
    }

    // handle the logout request by delegating refresh token deletion to the RefreshTokenService
    public void logout(String token){
        refreshTokenService.deleteRefreshToken(token);
    }
}
