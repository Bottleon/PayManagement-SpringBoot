package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.PWMissMatchException;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId){
        //orElseThrow -> not exist object
        return userRepository.findById(userId).orElseThrow(()->new IDNotExistException("존재하지 않은 ID입니다"));
    }

    @Override
    public User createUser(User user){
        user.setId(user.getId().replace("-","")); //user id(전화번호) 하이푼 제거

        //Optional : null값을 포함하거나 포함하지 않을 수 있는 wrapper객체
        Optional<User> isExistUser = userRepository.findById(user.getId());

        //Null test
        if(isExistUser.isPresent()){
            throw new IDDuplicatedException("이미 존재하는 ID입니다");
        }

        return userRepository.save(user);
    }

    @Override
    public User login(String id, String pw) {
        User user = getUserById(id);
        if(!user.getPassword().equals(pw)){
            throw new PWMissMatchException("비밀번호가 일치하지 않습니다.");
        }
        return userRepository.findUserByIdAndPassword(id,pw);
    }

}
