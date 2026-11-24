package com.example.itemmanagement.Service;

import com.example.itemmanagement.Entity.User;
import com.example.itemmanagement.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //UserRepository is the dependency this User service needs to communicate with database
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //here the constructor received the repository object through constructor dependency injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username ));
    }

   public User registerUser(String username, String password){

        if (userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, "USER");
        return userRepository.save(user);
   }
}
