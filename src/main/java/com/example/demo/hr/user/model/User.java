package com.example.demo.hr.user.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name="user_id")
    private String id;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="gender",nullable = false)
    private String gender;

    @Column(name="auth_type",nullable = false)
    private String authType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user")
    private List<UserStore> stores = new ArrayList<>();
}
