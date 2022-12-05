package com.example.demo.hr.pay.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="user_store_pay")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStorePay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_store_id",nullable = false)
    private UserStore userStore;

    @ManyToOne
    @JoinColumn(name="pay_code",nullable = false)
    private Pay pay;

    @Column(name="amount_money")
    @NotNull
    private int amountMoney;
}
