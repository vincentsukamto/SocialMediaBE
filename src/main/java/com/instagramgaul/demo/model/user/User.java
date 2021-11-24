package com.instagramgaul.demo.model.user;

import javax.persistence.*;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "username", nullable = false, length=30, unique=true)
    private String username;

    @Column(name = "email", nullable = false, length=80, unique=true)
    private String email;

    @JsonIgnore
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status = 0;

    @JsonIgnore
    @Column(name ="createdAt", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @JsonIgnore
    @Column(name ="updatedAt")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @JsonIgnore
    @Column(name ="deletedAt")
    private OffsetDateTime deletedAt;

    public User(){

    }

    public User(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

}