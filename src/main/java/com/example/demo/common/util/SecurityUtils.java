package com.example.demo.common.util;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityUtils {
    public static String getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
    }
    public static void sendAuthenticationException(HttpServletResponse response,String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // json 리턴 및 한글깨짐 수정.
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("error", HttpStatus.UNAUTHORIZED.value());
        json.put("message", message);
        json.put("status", HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
