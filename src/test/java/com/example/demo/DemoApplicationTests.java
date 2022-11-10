package com.example.demo;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserStoreRepository userStoreRepository;
	@Test
	void contextLoads() {
		Optional<User> optUser = userRepository.findById(01011111111L);
		Optional<Store> optStore = storeRepository.findById(1L);
		if(!optUser.isPresent()){
			throw new IllegalArgumentException();
		}
		if(!optStore.isPresent()){
			throw new IllegalArgumentException();
		}
		User user = optUser.get();
		Store store = optStore.get();

		UserStore userStore = new UserStore();
		userStore.setUser(user);
		userStore.setStore(store);

		userStoreRepository.save(userStore);
	}

}
