package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(String userId);
    public User createUser(User user);

    public User login(String id,String pw);

    public List<Store> getAllStores(String userId);
}
