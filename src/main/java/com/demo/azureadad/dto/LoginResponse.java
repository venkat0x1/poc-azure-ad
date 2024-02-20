package com.demo.azureadad.dto;

public class LoginResponse {

    private String message;

    private UserDTO userInfo;

    public LoginResponse(String message, UserDTO userDTO) {

        this.message = message;
        this.userInfo = userDTO;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }
}
