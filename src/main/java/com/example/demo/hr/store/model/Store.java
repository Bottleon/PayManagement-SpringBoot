package com.example.demo.hr.store.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="store_phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name="address")
    @NotNull
    private String address;

    @Column(unique = true)
    private String inviteCode;

    @OneToMany(mappedBy = "store")
    private List<UserStore> users = new ArrayList<>();
}
