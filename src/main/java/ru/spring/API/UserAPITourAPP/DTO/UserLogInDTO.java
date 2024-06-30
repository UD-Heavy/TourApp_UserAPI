package ru.spring.API.UserAPITourAPP.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserLogInDTO {
    @Column(name = "email")
    @NotEmpty(message = "Email should be not empty")
    @Size(max = 100, message = "Email should be shorter than 100 characters")
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Email should be not empty")
    @Size(max = 100, message = "Email should be shorter than 100 characters")
    private String password;

    public UserLogInDTO() {
    }

    public UserLogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
