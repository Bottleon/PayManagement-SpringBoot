package com.example.demo.hr.pay.repository;

import com.example.demo.hr.pay.model.UserStorePay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStorePayRepository extends JpaRepository<UserStorePay,Long> {
}
