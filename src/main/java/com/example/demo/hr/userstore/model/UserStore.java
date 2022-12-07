package com.example.demo.hr.userstore.model;

import com.example.demo.common.converter.BooleanToYNConverter;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.compositkey.UserStoreId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_store")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStore {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name="accept_status")
    private boolean acceptStatus = false;

    @Column(name="hourly_wage")
    private String hourlyWage;
}
