package com.example.demo.common.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.common.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper=new ObjectMapper();

    @Value("${jwt.secret}")
    private String secretKey;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("인증시도------------------------------");
        UsernamePasswordAuthenticationToken authenticationToken;

//        if(request.getContentType().equals(APPLICATION_JSON_VALUE)){ //aplication/json형태면
//            try {
//                log.info("json------------------------------");
//                User user = objectMapper.readValue(request.getReader().lines().collect(Collectors.joining()),User.class);
//                log.info(user.toString());
//                System.out.println("user id : "+user.getUsername()+", user pw : "+user.getPassword());
//                authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new AuthenticationServiceException("Request Content-Type(application/json) Parsing Error");
//            }
//        }else{
//            //form-request
//            String username = obtainUsername(request);
//            String password = obtainPassword(request);
//            authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
//        }
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        log.info("id :{}, password :{}",id,password);
        authenticationToken = new UsernamePasswordAuthenticationToken(id,password);
        log.info("인증시도------------------------------");
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //User user = (User) authentication.getPrincipal();
        log.info(authentication.getName());
        log.error("secret key : {}",secretKey);
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        String access_token = JWT.create()
                .withSubject(authentication.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(authentication.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        /*response.setHeader("access_token",access_token);
        response.setHeader("refresh_token",refresh_token);*/
        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }

}
