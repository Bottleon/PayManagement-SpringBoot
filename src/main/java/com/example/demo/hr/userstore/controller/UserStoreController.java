package com.example.demo.hr.userstore.controller;

import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.service.UserStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/user-store/*"})
public class UserStoreController {
    private final UserStoreService userStoreService;

    @GetMapping("/one")
    public ResponseEntity<UserStore> getUserStore(@RequestParam("userId") String userId,@RequestParam("storeId") String storeId){
        return ResponseEntity.ok(userStoreService.getUserStore(userId,storeId));
    }

    @GetMapping("/related-one")
    public ResponseEntity<UserStore> getRelatedUserStore(@RequestParam("storeId") String storeId){
        return ResponseEntity.ok(userStoreService.getRelatedUserStore(storeId));
    }

    @GetMapping("/stores-related-user")
    public ResponseEntity<List<UserStore>> getUserStoreListByUserId(@RequestParam("userId") String userId){
        return ResponseEntity.ok(userStoreService.getUserStoreListByUserId(userId));
    }
}
