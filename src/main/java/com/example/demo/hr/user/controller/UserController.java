package com.example.demo.hr.user.controller;

import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = {"/user/*"})
public class UserController {
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user.getId(),user.getPassword());
    }
}
