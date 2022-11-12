package com.example.demo.hr.user.controller;

import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = {"/user/*"})
public class UserController {
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @Valid final String id) {
            return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.login(user.getId(),user.getPassword()));
    }
}
