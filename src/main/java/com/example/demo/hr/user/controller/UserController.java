package com.example.demo.hr.user.controller;

import com.example.demo.common.token.TokenInfo;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(value = {"/user/*"})
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final String id) {
            return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    //가게 리스트
    @GetMapping("/stores")
    @JsonIgnore
    public ResponseEntity<List<Store>> getAllStores(@RequestParam String userId){
        return ResponseEntity.ok(userService.getAllStores(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user.getId(),user.getPassword()));
    }
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
}
