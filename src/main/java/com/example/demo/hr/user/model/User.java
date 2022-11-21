package com.example.demo.hr.user.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    public User(String id,String password){
        this.id=id;
        this.password=password;
    }
    @Id
    @Column(name="user_id",length=13)
    @Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$")
    private String id;

    @Column(name="password",length = 150)
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
    @NotNull(message = "비밀번호를 입력해 주세요")
    private String password;

    @Column(name="name",length=20)
    @NotNull(message = "이름을 입력해 주세요")
    private String name;

    @Column(name="gender",length=6)
    @NotNull(message = "성별을 선택해 주세요")
    private String gender;

    @Column(name="auth_type",length=9)
    @NotNull(message = "근로자 혹은 고용주를 선택해 주세요")
    private String authType;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createDate;

    @Column(name="profile_name",length=50)
    private String profileName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserStore> stores = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    //@JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
