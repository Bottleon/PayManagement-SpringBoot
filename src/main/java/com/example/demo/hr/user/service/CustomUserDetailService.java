package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.PWMissMatchException;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,BadCredentialsException {
        UserDetails userDetails;
            userDetails = userRepository.findById(username)
                    .map(this::createUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        return userDetails;
    }
    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(User user) {
        return User.builder()
                .id(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
