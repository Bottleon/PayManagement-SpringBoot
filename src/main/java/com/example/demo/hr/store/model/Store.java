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
    @NotNull(message = "가게이름을 입력해 주세요")
    private String name;

    @Column(name="store_phone_number")
    @NotNull(message = "가게번호를 입력해 주세요")
    private String phoneNumber;

    @Column(name="address")
    @NotNull(message = "가게주소를 입력해 주세요")
    private String address;

    @Column(unique = true)
    private String inviteCode;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<UserStore> users = new ArrayList<>();
}
