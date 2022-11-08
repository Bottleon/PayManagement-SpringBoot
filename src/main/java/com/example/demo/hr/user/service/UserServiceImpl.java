package com.example.demo.hr.user.service;

import com.example.demo.common.exception.ResourceNotFoundException;
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
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(()->
                                     new ResourceNotFoundException(userId+" 사용자를 찾을 수 없습니다.")
                             );
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(Long id, String pw) {
        return userRepository.findUserByIdAndPassword(id,pw);
    }

}
