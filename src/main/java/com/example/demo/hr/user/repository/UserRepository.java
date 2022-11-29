package com.example.demo.hr.user.repository;

import com.example.demo.hr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findUserByIdAndPassword(String id,String pw);
    User findUserByIdAndAuthType(String id,String authType);

    User findByName(String username);
}
