package com.example.demo.hr.userstore.controller;

import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.service.UserStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/user-store/*"})
public class UserStoreController {
    private final UserStoreService userStoreService;

    @GetMapping("/one")
    public ResponseEntity<UserStore> getUserStore(@RequestParam("userId") String userId,@RequestParam("storeId") String storeId){
        return ResponseEntity.ok(userStoreService.getUserStore(userId,storeId));
    }
}
