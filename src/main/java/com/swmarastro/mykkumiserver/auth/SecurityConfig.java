package com.swmarastro.mykkumiserver.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정 비활성화
                .csrf().disable()
                // 인증 및 인가 설정
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll() // 모든 요청에 대해 인증 없이 접근 허용
                )
                // 폼 로그인 비활성화
                .formLogin().disable()
                // OAuth2 로그인 설정
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/oauth2/authorization/kakao") // 카카오 로그인 페이지 설정 (필요에 따라 변경 가능)
                );

        return http.build();
    }
}

