package com.example.demo.hr.user.repository;

import com.example.demo.hr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByIdAndPassword(Long id,String pw);
}
