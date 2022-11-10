package com.example.demo.hr.user.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name="user_id")
    private Long id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private String authType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
}
