package io.github.vshnv.nortviz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_pk")
    private Integer id;
    @Column(name = "username", unique = true)
    @NotBlank(message = "username is mandatory")
    @Size(min = 8, max = 32, message = "Username must be between 8 - 32 characters")
    private String username;
    @Column(name = "hashed_password")
    @NotBlank(message = "Password is mandatory")
    private String hashedPassword;
    @Column(name = "name", unique = true)
    @NotBlank(message = "name is mandatory")
    private String name;
    @Column(name = "email", unique = true)
    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    public User() {}

    public User(String username, String hashedPassword, String name, String email) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
