package com.example.demo;

import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import com.example.demo.hr.user.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/*@Bean
	CommandLineRunner run(UserService userService) {
		return args->{
			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_WORKER"));
//			userService.saveRole(new Role(null,"ROLE_EMPLOYER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));

//			userService.saveUser(User.builder()
//										.id("01011111111")
//										.gender("남성")
//										.name("성환")
//										.authType("근로자")
//										.password("12341234a")
//										.roles(new ArrayList<>())
//										.build());
//			userService.saveUser(User.builder()
//					.id("01011111112")
//					.gender("여성")
//					.name("지미")
//					.authType("고용주")
//					.password("12341234a")
//					.roles(new ArrayList<>())
//					.build());
			userService.addRoleToUser("01011111111","ROLE_USER");
			userService.addRoleToUser("01011111112","ROLE_USER");
//			userService.addRoleToUser("01011111111","ROLE_WORKER");
//			userService.addRoleToUser("01011111112","ROLE_EMPLOYER");
//			userService.addRoleToUser("01011111112","ROLE_ADMIN");

		};
	}*/
}
