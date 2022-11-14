package com.example.demo.hr.store.service;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {
    public Boolean checkPlaceOfBusinessNumber(String id) throws JsonProcessingException;
    public Store createStore(Store store, User user) throws JsonProcessingException;

    public UserStore acceptUserInStore(Store store);

    public String createInviteCode();
}
