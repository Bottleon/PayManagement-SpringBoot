package com.example.demo.hr.store.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="store")
public class Store {
    @Id
    @Column(name="store_id")
    @Pattern(regexp = "/([0-9]{3})-?([0-9]{2})-?([0-9]{5})/")
    private String id; //사업자등록번호 :505-02-95947

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

    //사업자 규모(5인 미만,5인 이상)
    @Column(name="size", nullable = false)
    private String size;

    //지각 허용시간
    @Column(name="tardy_allow_time")
    private String tardyAllowTime;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<UserStore> users = new ArrayList<>();
}
