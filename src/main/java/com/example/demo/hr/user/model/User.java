package com.example.demo.hr.user.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *  --Fetch Default--
        OneToMany: LAZY
        ManyToOne: EAGER
        OneToOne: EAGER
        ManyToMany: LAZY
 * */
@Data
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$")
    private String id;

    @Column(name="password")
    @NotNull
    private String password;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="gender")
    @NotNull
    private String gender;

    @Column(name="auth_type")
    @NotNull
    private String authType;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user")
    private List<UserStore> stores = new ArrayList<>();
}
