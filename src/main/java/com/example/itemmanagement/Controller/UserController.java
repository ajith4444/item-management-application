package com.example.itemmanagement.Controller;

import com.example.itemmanagement.DTO.AuthResponseDTO;
import com.example.itemmanagement.DTO.LoginRequestDTO;
import com.example.itemmanagement.DTO.RefreshTokenRequestDTO;
import com.example.itemmanagement.DTO.RefreshTokenResponseDTO;
import com.example.itemmanagement.Entity.User;
import com.example.itemmanagement.Service.AuthService;
import com.example.itemmanagement.Service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService= authService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(
                user.getUsername(),
                user.getPassword()
        );
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authService.login(loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword());
    }

    @PostMapping("/refresh")
    public RefreshTokenResponseDTO authRefresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return authService.refreshAccessToken(refreshTokenRequestDTO.getRefreshToken());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
         authService.logout(refreshTokenRequestDTO.getRefreshToken());
    }

}
