package com.swmarastro.mykkumiserver.global.config;

import com.swmarastro.mykkumiserver.auth.LoginUserArgumentResolver;
import com.swmarastro.mykkumiserver.auth.token.TokenService;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;
    private final TokenService tokenService;

    @Bean
    public LoginUserArgumentResolver loginUserArgumentResolver() {
        return new LoginUserArgumentResolver(userService, tokenService);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver());
    }
}
