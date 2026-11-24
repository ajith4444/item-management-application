package com.example.itemmanagement.Service;

import com.example.itemmanagement.Entity.User;
import com.example.itemmanagement.Repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
        return new CustomUserDetails(user); //used new to create a CustomUserDetails object, because spring security expects on obj that implements UserDetails
    }
}
