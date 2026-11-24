package com.example.itemmanagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration // it tells to spring that this class is responsible for configuration instructions, and it processes when it's setting up the application
public class SecurityConfig {
    //Here we are securing the Http requests through the security Filter chain object and configuring all the rules inside this method through this(SecurityFilterChain object)
    //before it reaches to controller, it checks all the rules which we define here
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/auth/register", "/auth/login", "/auth/refresh"))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login", "/auth/refresh").permitAll()
                .anyRequest().authenticated());
        return httpSecurity.build(); // httpSecurity.build() - builds the configured Security filter chain
    }

    @Bean
    //since the password never store the direct value, we should use this method to convert the password into secure hash (Hashing(Password)) before save in the database
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
}
