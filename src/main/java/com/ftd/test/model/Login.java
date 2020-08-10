package com.ftd.test.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Login {

    @NotEmpty(message = "El campo no debe ir vacío")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @NotEmpty(message = "El campo no debe ir vacío")
    private String password;

    public Login(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }
}
