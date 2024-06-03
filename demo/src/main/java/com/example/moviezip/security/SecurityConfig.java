package com.example.moviezip.security;

import com.example.moviezip.domain.User;
import com.example.moviezip.service.UserService;
import com.example.moviezip.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserServiceImpl userServiceimpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 방식 설정
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/login", "/api/auth/register").permitAll() // 로그인과 회원가입 엔드포인트는 인증 없이 접근 가능
                .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login") // 로그인 엔드포인트 설정
                .defaultSuccessUrl("/main", true) // 로그인 성공 시 리다이렉트 URL 설정
                .and()
                .logout()
                .logoutUrl("/api/auth/logout") // 로그아웃 엔드포인트 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공 시 리다이렉트 URL 설정
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID"); // 쿠키 삭제
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // AuthenticationManager 빈 설정
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userId -> {
            User user = userServiceimpl.findByUserId(userId);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserId()) //userId로 사용자 설정
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        };
    }
}