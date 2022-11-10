package com.example.demo.hr.userstore.repository;

import com.example.demo.hr.userstore.compositkey.UserStoreId;
import com.example.demo.hr.userstore.model.UserStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoreRepository extends JpaRepository<UserStore, UserStoreId> {
}
