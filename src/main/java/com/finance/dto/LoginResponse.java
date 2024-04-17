package com.finance.dto;

public class LoginResponse extends ApiResponse {

    private final String token;

    public LoginResponse(int resultCode, String resultMsg, String token) {
        super(resultCode, resultMsg);
        this.token = token;
        //TODO Auto-generated constructor stub
    }

    public String getToken() {
        return token;
    }
    
}
