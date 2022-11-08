package com.example.demo.hr.user.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    private Long id;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private String authType;
}
