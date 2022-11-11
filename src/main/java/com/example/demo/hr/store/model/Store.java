package com.example.demo.hr.store.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
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

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="store_phone_number",nullable = false)
    private String phoneNumber;

    @Column(name="address",nullable = false)
    private String address;

    @Column(unique = true)
    private String inviteCode;

    @OneToMany(mappedBy = "store")
    private List<UserStore> users = new ArrayList<>();
}
