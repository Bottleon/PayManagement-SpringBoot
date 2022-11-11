package com.example.demo.hr.user.service;

import com.example.demo.hr.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(String userId);
    public User createUser(User user);

    public User login(String id,String pw);
}
