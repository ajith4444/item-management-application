package com.example.itemmanagement.Service;

import com.example.itemmanagement.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    //here supplying the user object(dependency) through the constructor
    public CustomUserDetails(User user){
        this.user = user; //assignment operator
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public  String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //Has this account is expired?.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //Has this account locked or unlocked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //UserDetails provides method , has the user's password /credentials expired
        return true;
    }

    @Override
    public boolean isEnabled() { // Has this account active or disabled
        return true;
    }
}
