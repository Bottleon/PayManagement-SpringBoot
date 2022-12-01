package com.example.demo.hr.store.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="store")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @Column(name="store_id")
    @Pattern(regexp = "^\\d{3}-?\\d{2}-?\\d{5}$")
    private String id; //사업자등록번호 :505-02-95947

    @Column(name="name")
    @NotNull(message = "가게이름을 입력해 주세요")
    private String name;

    @Column(name="store_phone_number")
    @NotNull(message = "가게번호를 입력해 주세요")
    private String phoneNumber;

    @Column(name="basic_address")
    @NotNull(message = "가게주소를 입력해 주세요")
    private String basicAddress;

    @Column(name="detail_address")
    @NotNull(message = "가게주소를 입력해 주세요")
    private String detailAddress;

    //사업자 규모(5인 미만,5인 이상)
    @Column(name="size", nullable = false)
    private String size;

    //지각 허용시간
    @Column(name="late_allow_time")
    private String lateAllowTime;

    //쉬는시간
    @Column(name="break_time")
    private String breakTime;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserStore> users = new ArrayList<>();
}
