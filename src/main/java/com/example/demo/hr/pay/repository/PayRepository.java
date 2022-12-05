package com.example.demo.hr.pay.repository;

import com.example.demo.hr.pay.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay,Long> {
    public Pay findPayByPayType(String type);
}
