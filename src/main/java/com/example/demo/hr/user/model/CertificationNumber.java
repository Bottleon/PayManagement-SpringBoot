package com.example.demo.hr.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_certification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationNumber {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(unique = true,nullable = false)
    private String certificationNumber;
}
