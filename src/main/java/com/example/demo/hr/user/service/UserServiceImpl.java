package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.NotExistStore;
import com.example.demo.common.provider.JwtTokenProvider;
import com.example.demo.common.redis.RedisUtil;
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
import org.hibernate.Hibernate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
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
    private final RedisUtil redisUtil;
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(String userId){
        log.error(userId);
        //orElseThrow -> not exist object
        return userRepository.findById(userId).orElseThrow(()->new IDNotExistException("???????????? ?????? ID?????????"));
    }

    @Override
    @Transactional
    public User saveUser(User user){
        user.setId(user.getId().replace("-","")); //user id(????????????) ????????? ??????

        //Optional : null?????? ??????????????? ???????????? ?????? ??? ?????? wrapper??????
        Optional<User> isExistUser = userRepository.findById(user.getId());

        //Null test
        if(isExistUser.isPresent()){
            throw new IDDuplicatedException("?????? ???????????? ID?????????");
        }

        addRoleToUser(user,"ROLE_USER");
        if(user.getAuthType().equals("?????????")){
            addRoleToUser(user,"ROLE_WORKER");
        }else{
            addRoleToUser(user,"ROLE_EMPLOYER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        //redisUtil.deleteData(user.getId());
        return user;
    }

    @Override
    @Transactional
    public TokenInfo login(String id, String pw) {
        // 1. Login ID/PW ??? ???????????? Authentication ?????? ??????
        // ?????? authentication ??? ?????? ????????? ???????????? authenticated ?????? false
        log.error(id+"=============="+pw);
        UsernamePasswordAuthenticationToken authenticationToken;
        try{
            authenticationToken = new UsernamePasswordAuthenticationToken(id, pw);
        }catch(BadCredentialsException bce){
            throw new BadCredentialsException("????????? ?????? ??????????????? ???????????? ????????????.");
        }
        // 2. ?????? ?????? (????????? ???????????? ??????)??? ??????????????? ??????
        // authenticate ???????????? ????????? ??? CustomUserDetailsService ?????? ?????? loadUserByUsername ???????????? ??????
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. ?????? ????????? ???????????? JWT ?????? ??????
        return jwtTokenProvider.generateToken(authentication);
    }
    @Override
    @Transactional
    public void addRoleToUser(User user , String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

}
