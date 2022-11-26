package com.example.demo.hr.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationNumber {
    private String id;
    private String certificationNumber;
}
