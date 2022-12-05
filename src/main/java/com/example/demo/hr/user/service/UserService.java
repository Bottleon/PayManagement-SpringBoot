package com.example.demo.hr.user.service;

import com.example.demo.common.token.TokenInfo;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService{
    public List<User> getAllUsers();
    public User getUserById(String userId);
    public User saveUser(User user);

    public TokenInfo login(String id, String pw);

    public List<Store> getAllStores(String userId);
    public void addRoleToUser(User user,String roleName);

}
