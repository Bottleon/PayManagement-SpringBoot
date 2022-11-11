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

    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String address;

    @Column(unique = true)
    private String inviteCode;

    @OneToMany(mappedBy = "store")
    private List<UserStore> users = new ArrayList<>();
}
