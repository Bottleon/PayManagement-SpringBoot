package com.example.demo.hr.pay.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="user_store_pay")
public class UserStorePay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id",nullable = false),
            @JoinColumn(name="store_id",nullable = false)
    })
    private UserStore userStore;

    @ManyToOne
    @JoinColumn(name="pay_code",nullable = false)
    private Pay pay;

    @Column(name="amount_money")
    @NotNull
    private int amountMoney;
}
