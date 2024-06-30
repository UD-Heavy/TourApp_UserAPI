package ru.spring.API.UserAPITourAPP.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    @Size(max = 100, message = "Login should be shorter than 100 characters")
    private String login;

    @Column(name = "email")
    @NotEmpty(message = "Email should be not empty")
    @Size(max = 100, message = "Email should be shorter than 100 characters")
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password should be not empty")
    @Size(max = 100, message = "Password should be shorter than 100 characters")
    private String password;

    public User() {
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
