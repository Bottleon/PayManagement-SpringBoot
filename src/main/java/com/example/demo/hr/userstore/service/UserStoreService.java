package com.example.demo.hr.userstore.service;

import com.example.demo.hr.userstore.model.UserStore;

import java.util.List;


public interface UserStoreService {
    public UserStore getUserStore(String userId,String storeId);
    public UserStore getRelatedUserStore(String storeId);
    public List<UserStore> getUserStoreListByUserId(String userId);
}
