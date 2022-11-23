package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.NotExistStore;
import com.example.demo.common.provider.JwtTokenProvider;
import com.example.demo.common.token.TokenInfo;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.RoleRepository;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserStoreRepository userStoreRepository;
    private final StoreRepository storeRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId){
        log.error(userId);
        //orElseThrow -> not exist object
        return userRepository.findById(userId).orElseThrow(()->new IDNotExistException("존재하지 않은 ID입니다"));
    }

    @Override
    public User saveUser(User user){
        user.setId(user.getId().replace("-","")); //user id(전화번호) 하이푼 제거

        //Optional : null값을 포함하거나 포함하지 않을 수 있는 wrapper객체
        Optional<User> isExistUser = userRepository.findById(user.getId());

        //Null test
        if(isExistUser.isPresent()){
            throw new IDDuplicatedException("이미 존재하는 ID입니다");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        addRoleToUser(user.getId(),"ROLE_USER");
        if(user.getAuthType().equals("근로자")){
            addRoleToUser(user.getId(),"ROLE_WORKER");
        }else{
            addRoleToUser(user.getId(),"ROLE_EMPLOYER");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public TokenInfo login(String id, String pw) {
        log.debug(id+"------------"+pw);
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, pw);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication;
        try{
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        }catch(BadCredentialsException bce){
            throw new BadCredentialsException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public List<Store> getAllStores(String userId) {
        List<UserStore> userStores = userStoreRepository.findUserStoreByUser_Id(userId);
        List<Store> stores = new ArrayList<>();
        for(UserStore us : userStores){
            stores.add(storeRepository.findById(us.getStore().getId()).orElseThrow(()->new NotExistStore("서버 내부 오류")));
        }
        return stores;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userId , String roleName) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IDNotExistException("사용자를 찾을 수 없음"));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

}
