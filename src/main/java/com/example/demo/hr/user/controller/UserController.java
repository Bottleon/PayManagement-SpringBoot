package com.example.demo.hr.user.controller;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.RoleToUserForm;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = {"/user/*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private User user;

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
    public ResponseEntity<List<Store>> getAllStores(@RequestParam String userId){
        return ResponseEntity.ok(userService.getAllStores(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user.getId(),user.getPassword()));
    }
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserId(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
