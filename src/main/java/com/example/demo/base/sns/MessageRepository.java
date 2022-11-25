package com.example.demo.base.sns;


import com.example.demo.hr.user.model.CertificationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<CertificationNumber,String> {
}
