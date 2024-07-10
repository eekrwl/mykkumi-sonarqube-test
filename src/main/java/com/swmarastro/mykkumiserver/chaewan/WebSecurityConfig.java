//package com.swmarastro.mykkumiserver.chaewan;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    private final OAuth2UserService oAuth2UserService;
//
//    public WebSecurityConfig(OAuth2UserService oAuth2UserService) {
//        this.oAuth2UserService = oAuth2UserService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(config -> config.anyRequest().permitAll());
//        http.oauth2Login(oauth2Configurer -> oauth2Configurer
//                .loginPage("/login")
//                .successHandler(successHandler())
//                .userInfoEndpoint(userInfo -> userInfo
//                        .userService(oAuth2UserService)  // OAuth2 사용자 서비스 설정
//                ));
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return ((request, response, authentication) -> {
//            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//
//            String id = defaultOAuth2User.getAttributes().get("id").toString();
//            String body = """
//                    {"id":"%s"}
//                    """.formatted(id);
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//            PrintWriter writer = response.getWriter();
//            writer.println(body);
//            writer.flush();
//        });
//    }
//
//
//    private final UserDetailsService userService;
//    private final TokenProvider tokenProvider;
//
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/static/**");
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return http.build();
//    }
//
//}
