package com.example.demo.hr.user.controller;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/user/*"})
public class UserController {
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final String id) {
            return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.createUser(user));
    }
    //가게 리스트
    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getAllStores(@RequestParam String userId){
        return ResponseEntity.ok(userService.getAllStores(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user.getId(),user.getPassword()));
    }
}
