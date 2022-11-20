package com.example.demo.hr.user.repository;

import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
