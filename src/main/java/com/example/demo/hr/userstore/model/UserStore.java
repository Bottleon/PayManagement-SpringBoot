package com.example.demo.hr.userstore.model;

import com.example.demo.common.converter.BooleanToYNConverter;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.compositkey.UserStoreId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_store")
@IdClass(UserStoreId.class)
public class UserStore {

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name="accept_status")
    private boolean acceptStatus;

    @Column(name="hourly_wage")
    private String hourlyWage;
}
