/*
package com.example.demo;

import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.hr.store.controller.StoreController;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.controller.UserController;
import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.RoleRepository;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserStoreRepository userStoreRepository;
	@Autowired
	UserController userController;
	@Autowired
	StoreController storeController;
    @Autowired
    RoleRepository roleRepository;
	User worker;
	User employer;
	Store store;
	String[] genders = new String[]{"남성","여성"};
	@Test
	public void test(){
		store = Store.builder()
				.id("505-02-95947")
				.basicAddress("대구 달서구 신당동 11-1")
				.detailAddress("301호")
				.breakTime("60")
				.lateAllowTime("10")
				.name("닭발")
				.size("5")
				.phoneNumber("053-111-1111")
				.build();
		employer = userRepository.findById("01011111112").orElseThrow(()->new IDNotExistException("유저없음"));
		UserStore userStore = UserStore.builder()
				.store(store)
				.user(employer)
				.build();
		storeRepository.save(store);
		userStoreRepository.save(userStore);
	}
	@Test
	public void db삭제(){
		userRepository.deleteAll();
		storeRepository.deleteAll();
		userStoreRepository.deleteAll();
	}
	//유저 회원가입했을 때
	@Test
	@Order(1)
	void 회원가입(){
        roleRepository.save(new Role(null,"ROLE_USER"));
        roleRepository.save(new Role(null,"ROLE_WORKER"));
		roleRepository.save(new Role(null,"ROLE_EMPLOYER"));
		roleRepository.save(new Role(null,"ROLE_ADMIN"));
		List<User> users = new ArrayList<>();
		for(int i=1; i<10; i++) {
			String inputId = "0101111111"+i;
			User user = new User();
			user.setId(inputId);
			user.setAuthType("근로자");
			user.setGender(genders[i%2]);
			user.setName("가가");
			user.setPassword("12341234a!");
			users.add(user);
		}
		User user = new User();
		user.setId("01055555555");
		user.setAuthType("고용주");
		user.setGender("여성");
		user.setName("나나");
		user.setPassword("12341234a!");
		users.add(user);
        for(User u : users){
            userController.saveUser(u);
        }
	}

}
*/
