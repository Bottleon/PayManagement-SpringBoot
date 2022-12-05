package com.example.demo.hr.userstore.repository;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.compositkey.UserStoreId;
import com.example.demo.hr.userstore.model.UserStore;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Repository
public interface UserStoreRepository extends JpaRepository<UserStore, Long> {
    public List<UserStore> findUserStoreByUserId(String user_id);
    @EntityGraph(attributePaths = {"user","store"})
    public UserStore findUserStoreByUserIdAndStoreId(String user_id,String store_id);

}
