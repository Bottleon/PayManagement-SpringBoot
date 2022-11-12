package com.example.demo;

import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.controller.UserController;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserStoreRepository userStoreRepository;
	@Autowired
	UserController userController;
	User worker;
	User employer;
	Store store;
	String[] genders = new String[]{"남성","여성"};
	//유저 회원가입했을 때
	@Test
	@Order(1)
	void 회원가입(){

		//		List<User> users = new ArrayList<>();
//		for(int i=1; i<10; i++) {
//			String inputId = "0101111111"+i;
//			User user = new User();
//			user.setId(inputId);
//			user.setAuthType("근로자");
//			user.setGender(genders[i%2]);
//			user.setName("가가");
//			user.setPassword("a");
//			users.add(user);
//		}
//		User user = new User();
//		user.setId("010-5555-5555");
//		user.setAuthType("고용주");
//		user.setGender("여성");
//		user.setName("나나");
//		user.setPassword("b");
//		users.add(user);
//		for(User u:users){
//			userController.createUser(u);
//		}
	}

	@Test
	@Order(2)
	void 로그인(){
		worker = userController
				.login(userRepository.findUserByIdAndAuthType("01011111111","근로자"));
		employer = userController
				.login(userRepository.findUserByIdAndAuthType("01055555555","고용주"));
	}
}
