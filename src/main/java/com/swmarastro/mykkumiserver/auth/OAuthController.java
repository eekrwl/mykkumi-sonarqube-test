package com.swmarastro.mykkumiserver.auth;

import com.swmarastro.mykkumiserver.auth.oauth.OAuthProvider;
import com.swmarastro.mykkumiserver.auth.oauth.dto.SigninRequest;
import com.swmarastro.mykkumiserver.auth.oauth.dto.SigninResponse;
import com.swmarastro.mykkumiserver.auth.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OAuthController {

    private final OAuthService oAuthService;

    @PostMapping("/signin/kakao")
    public ResponseEntity<SigninResponse> signinKakao(@RequestBody SigninRequest request) {
        log.info("api 들어옴");
        SigninResponse signin = oAuthService.signin(request);
        return ResponseEntity.ok()
                .body(signin);
    }

}
