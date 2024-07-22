package com.swmarastro.mykkumiserver.auth;

import com.swmarastro.mykkumiserver.auth.dto.*;
import com.swmarastro.mykkumiserver.auth.service.AuthService;
import com.swmarastro.mykkumiserver.auth.token.TokenService;
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
    private final TokenService tokenService;

    @PostMapping("/signin/kakao")
    public ResponseEntity<SigninResponse> signinKakao(@RequestBody KakaoSigninRequest request) {
        SigninResponse signinResponse = authService.signin(request, OAuthProvider.KAKAO);
        return ResponseEntity.ok()
                .body(signinResponse);
    }

    @PostMapping("/signin/apple")
    public ResponseEntity<SigninResponse> signinApple(@RequestBody AppleSigninRequest request) {
        SigninResponse signinResponse = authService.signin(request, OAuthProvider.APPLE);
        return ResponseEntity.ok()
                .body(signinResponse);
    }

    @PostMapping("/token")
    public ResponseEntity<NewAccessTokenResponse> getNewAccessToken(@RequestBody NewAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        NewAccessTokenResponse newAccessTokenResponse = NewAccessTokenResponse.of(newAccessToken);
        return ResponseEntity.ok()
                .body(newAccessTokenResponse);
    }
}
