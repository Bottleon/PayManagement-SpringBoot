package com.example.demo.hr.userstore.service;


import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStoreServiceImpl implements UserStoreService {
    private final UserStoreRepository userStoreRepository;
    @Override
    public UserStore getUserStore(String userId,String storeId) {
        return userStoreRepository.findUserStoreByUserIdAndStoreId(userId,storeId);
    }
}
