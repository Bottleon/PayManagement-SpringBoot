package com.example.demo.hr.pay.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name="pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pay_code")
    private Long code;

    @Column(name="pay_type")
    @NotNull
    private String payType;
}
