package com.example.demo.hr.user.service;

import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                             .orElseThrow(()->
                                     new RuntimeException("abc")
                             );
    }

    @Override
    public User createUser(User user) {
        user.setId(user.getId().replace("-","")); //user id(전화번호) 하이푼 제거
        return userRepository.save(user);
    }

    @Override
    public User login(String id, String pw) {
        return userRepository.findUserByIdAndPassword(id,pw);
    }

}
