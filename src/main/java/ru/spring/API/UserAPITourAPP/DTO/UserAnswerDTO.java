package ru.spring.API.UserAPITourAPP.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserAnswerDTO {
    @Column(name = "login")
    @NotEmpty(message = "Login should be not empty")
    @Size(max = 100, message = "Login should be shorter than 100 characters")
    private String login;

    @Column(name = "email")
    @NotEmpty(message = "Email should be not empty")
    @Size(max = 100, message = "Email should be shorter than 100 characters")
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Email should be not empty")
    @Size(max = 100, message = "Email should be shorter than 100 characters")
    private String password;

    public UserAnswerDTO() {
    }

    public UserAnswerDTO(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
