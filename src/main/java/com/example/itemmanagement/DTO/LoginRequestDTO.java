package com.example.itemmanagement.DTO;

//purpose of using this DTO-to hold the incoming JSON in a Java object, instead of using User entity we are using this, because login needs 2 pieces of info(uname, pwd)
public class LoginRequestDTO {

    private String username;
    private String password;

    public LoginRequestDTO(){
    }

    public  LoginRequestDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
