package com.example.demo.hr.store.controller;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.service.StoreService;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/store/*")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/business/{id}")
    public ResponseEntity<Boolean> checkPlaceOfBusinessNumber(@PathVariable String id) throws JsonProcessingException, UnsupportedEncodingException {
        return ResponseEntity.ok(storeService.checkPlaceOfBusinessNumber(id));
    }

    @PostMapping("/managed-store")
    public ResponseEntity<Store> createStore(@RequestBody @Valid Store store,@RequestBody @Valid User user) throws JsonProcessingException {
        return ResponseEntity.ok(storeService.createStore(store,user));
    }

    //초대코드 생성
    @GetMapping("/managed-store/code")
    public ResponseEntity<String> createInviteCode(){
        return ResponseEntity.ok(storeService.createInviteCode());
    }

    //유저가 초대코드로 입장신청할 때 수락함수
    @PostMapping("/managed-store/user")
    public ResponseEntity<UserStore> acceptUserInStore(@RequestBody Store store){
        return ResponseEntity.ok(storeService.acceptUserInStore(store));
    }
}
