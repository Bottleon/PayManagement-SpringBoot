package com.example.demo;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserStoreRepository userStoreRepository;
	User user;
	Store store;
	String[] genders = new String[]{"남성","여성"};
	//유저 회원가입했을 때
	@Test
	@Order(1)
	void 회원가입(){
		List<User> users = new ArrayList<>();
		for(int i=1; i<11; i++) {
			System.out.println(genders[i%2]);
//			String inputId = "0101111111"+1;
//			User user = new User();
//			user.setId(inputId);
//			user.setAuthType("근로자");
//			user.setGender(genders[i%2]);
//			user.setName("가가");
//			user.setPassword("a");
//			users.add(user);
		}
//		User user = new User();
//		user.setId("01055555555");
//		user.setAuthType("고용주");
//		user.setGender("여성");
//		user.setName("나나");
//		user.setPassword("b");
//		users.add(user);
//		userRepository.saveAll(users);
	}
}
