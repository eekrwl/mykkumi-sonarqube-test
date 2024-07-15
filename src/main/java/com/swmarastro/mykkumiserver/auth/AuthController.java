package com.swmarastro.mykkumiserver.auth;

import com.swmarastro.mykkumiserver.auth.dto.SigninRequest;
import com.swmarastro.mykkumiserver.auth.dto.SigninResponse;
import com.swmarastro.mykkumiserver.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin/kakao")
    public ResponseEntity<SigninResponse> signinKakao(@RequestBody SigninRequest request) {
        SigninResponse signinResponse = authService.signin(request, OAuthProvider.KAKAO);
        return ResponseEntity.ok()
                .body(signinResponse);
    }
}
