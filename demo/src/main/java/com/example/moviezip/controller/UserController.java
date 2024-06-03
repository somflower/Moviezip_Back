package com.example.moviezip.controller;

import com.example.moviezip.dto.LoginRequest;
import com.example.moviezip.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        // 로그인 요청 처리
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword());

        // 추가적인 요청 정보를 설정
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 인증 시도
        Authentication authentication = authenticationManager.authenticate(authToken);

        // 인증이 성공하면 SecurityContextHolder에 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "Login successful";
    }

    @PostMapping("/register") //? 바꿔야될지도
    public String register(@RequestBody LoginRequest loginRequest) {
        // 회원가입 요청 처리
        userServiceImpl.registerUser(loginRequest.getUserId(), loginRequest.getPassword());
        return "User registered";
    }

}
