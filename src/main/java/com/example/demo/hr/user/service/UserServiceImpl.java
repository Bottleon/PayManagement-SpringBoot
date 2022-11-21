package com.example.demo.hr.user.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.NotExistStore;
import com.example.demo.common.exception.PWMissMatchException;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.model.Role;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.repository.RoleRepository;
import com.example.demo.hr.user.repository.UserRepository;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import jdk.internal.dynalink.support.NameCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService,UserDetailsService {
    private final UserRepository userRepository;
    private final UserStoreRepository userStoreRepository;
    private final StoreRepository storeRepository;
    private final RoleRepository roleRepository;
    private final ApplicationContext context;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(()->new IDNotExistException(id+" 사용자는 존재하지 않습니다."));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId){
        log.debug("+++++++++++++++++++"+userId);
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
        BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) context.getBean("passwordEncoder");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String id, String pw) {
        log.debug(id+"------------"+pw);
        User user = userRepository.findById(id).orElseThrow(()->new IDNotExistException(id+"가 존재하지 않습니다"));

        BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) context.getBean("passwordEncoder");
        if(!passwordEncoder.matches(pw,user.getPassword())){
            throw new PWMissMatchException("비밀번호가 일치하지 않습니다.");
        }

        return user;
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
