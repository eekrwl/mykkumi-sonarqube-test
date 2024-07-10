package com.swmarastro.mykkumiserver.chaewan;

import com.swmarastro.mykkumiserver.chaewan.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @GetMapping
    public String test() {
        log.info("moya");
        return jwtProperties.getIssuer()+" "+jwtProperties.getSecretKey();
        /*log.error("으아아아");
        User user = new User();
        user.setId(1L);
        user.setEmail("wani9909@naver.com");
        user.setIntroduction("하이");
        user.setNickname("완두콩");
        Duration duration = Duration.ofMinutes(5);
        String token = tokenProvider.generateToken(user, duration);
        return token;*/
    }
}
