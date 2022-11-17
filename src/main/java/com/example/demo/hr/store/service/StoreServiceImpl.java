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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService{
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserStoreRepository userStoreRepository;
    @Override
    public Boolean checkPlaceOfBusinessNumber(String id) throws JsonProcessingException, UnsupportedEncodingException {
        id = id.replace("-","");
        Map<String, Object> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        String jsonInString;
        String serviceKey = "qWOt5pG6ygmUTox7EXU2LvDcIeSKui+Q3ZaXaNZ1Iaxe95LXz4rL5yVkK989nj/3lSCmw6MWG4a68NRdAuQWyA==";

        String url="http://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="+serviceKey;
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> list = new ArrayList<>();
        list.add(id);
        jsonObject.put("b_no",list);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(jsonObject.toString(),header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST,entity, Object.class);

        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
        result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

        //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
        ObjectMapper mapper = new ObjectMapper();
        jsonInString = mapper.writeValueAsString(resultMap.getBody());
        log.info(jsonInString);
        return HttpStatus.OK == resultMap.getStatusCode();
    }

    @Override
    public Store createStore(Store store, User user) throws JsonProcessingException {
        Optional<Store> optStore=storeRepository.findById(store.getId());
        if(optStore.isPresent()){
            throw new IDDuplicatedException("이미 가입한 사업장입니다");
        }else{
            storeRepository.save(store);
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
