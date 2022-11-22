package com.example.demo.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@Slf4j
public class JWTUtils {
    public static void setErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        log.error("status :{}",response.getStatus());
        log.error("ex :",ex.getCause());
        final Map<String, Object> errors = new HashMap<>();
        response.setContentType(APPLICATION_JSON_VALUE);
        int status;
        String error;
        String message;
        if(ex instanceof UsernameNotFoundException){
            status = HttpServletResponse.SC_BAD_REQUEST;
            error = "Bad Request";
            message = "해당 아이디는 존재하지 않습니다";
            log.error("id");
        }else if(ex instanceof BadCredentialsException){
            status = HttpServletResponse.SC_BAD_REQUEST;
            error = "Bad Request";
            message = "해당 아이디는 존재하지 않습니다";
            log.error("pw");
        }else{
            status = HttpServletResponse.SC_UNAUTHORIZED;
            error = "Unauthorized";
            message = ex.getMessage();
            log.error("other");
        }

        errors.put("status", status);
        errors.put("error", error);
        errors.put("message", message);
        errors.put("path", request.getServletPath());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errors);
    }
    public static void getRefreshToken(HttpServletRequest request, HttpServletResponse response, UserService userService) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String userId = decodedJWT.getSubject();
                User user = userService.getUserById(userId);

                String access_token = JWT.create()
                        .withSubject(user.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch(Exception e){
                assert e instanceof AuthenticationException;
                setErrorResponse(request,response, (AuthenticationException) e);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
