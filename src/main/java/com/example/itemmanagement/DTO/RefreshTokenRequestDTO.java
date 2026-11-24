package com.example.itemmanagement.DTO;

public class RefreshTokenRequestDTO {

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequestDTO(String refreshToken){
        this.refreshToken= refreshToken;
    }

    public RefreshTokenRequestDTO(){

    }
}
