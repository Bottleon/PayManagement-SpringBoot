package com.example.demo.hr.store.service;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.hr.store.model.Store;
import com.example.demo.hr.store.repository.StoreRepository;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.hr.userstore.repository.UserStoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService{
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserStoreRepository userStoreRepository;
    @Override
    public Boolean checkPlaceOfBusinessNumber(String id) throws JsonProcessingException {
        id = id.replace("-","");
        Map<String, Object> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        String jsonInString;
        String serviceKey = "w6GaTMTVKsK9bAPWI5riSfEnXRifcRlmVRZo5eWkRIv0Pp9Kn8%2B92xuI7BnVJFD7sl0eSSZganb5JjbtOIgRMg%3D%3D";
        String url="https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey="+serviceKey;
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).query(id).build();

        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST,entity,Object.class);

        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
        result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

        //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
        ObjectMapper mapper = new ObjectMapper();
        jsonInString = mapper.writeValueAsString(resultMap.getBody());
        log.info(jsonInString);
        if(HttpStatus.OK==resultMap.getStatusCode()){
            return true;
        }
        return false;
    }

    @Override
    public Store createStore(Store store, User user) throws JsonProcessingException {
        Optional<Store> optStore=storeRepository.findById(store.getId());
        if(optStore.isPresent()){
            throw new IDDuplicatedException("이미 가입한 사업장입니다");
        }else{
            UserStore userStore = new UserStore();
            userStore.setStore(store);
            userStore.setUser(user);
            userStore.setAcceptStatus(true);
            userStoreRepository.save(userStore);
        }
        return store;
    }

    @Override
    public UserStore acceptUserInStore(Store store) {

        return null;
    }

    @Override
    public String createInviteCode() {
        return null;
    }
}
