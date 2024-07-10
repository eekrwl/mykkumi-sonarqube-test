package com.swmarastro.mykkumiserver.auth.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swmarastro.mykkumiserver.auth.oauth.OAuthProvider;
import com.swmarastro.mykkumiserver.auth.oauth.dto.CreateOauthUserRequest;
import com.swmarastro.mykkumiserver.auth.oauth.dto.SigninRequest;
import com.swmarastro.mykkumiserver.auth.oauth.dto.SigninResponse;
import com.swmarastro.mykkumiserver.auth.token.RefreshToken;
import com.swmarastro.mykkumiserver.auth.token.RefreshTokenService;
import com.swmarastro.mykkumiserver.auth.token.TokenService;
import com.swmarastro.mykkumiserver.user.User;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuthService {

    private final KakaoService kakaoService;
    private final UserService userService;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    //카카오에서 사용자 이메일 받아오기
    //사용자 회원가입시키기
    //우리 서비스 토큰 발급 (리프레시 90일, 엑세스 15분??)

    public SigninResponse signin(SigninRequest request) {
        log.info("oauth service 들어옴");
        //카카오에서 사용자 정보 받아오기
        CreateOauthUserRequest kakaoInfo = null;
        try {
            kakaoInfo = kakaoService.getKakaoInfo(request.getAccessToken());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage()); // TODO 나중에 제대로 처리
        }
        log.info("zero");
        //사용자 회원가입시키기
        User user = userService.saveUser(OAuthProvider.KAKAO, kakaoInfo.getEmail());
        log.info("one");
        //우리 서비스 토큰 발급
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        String accessToken = null;
        try {
            accessToken = tokenService.createNewAccessToken(refreshToken.getRefreshToken());
        } catch (Exception e) {
            log.error(e.getMessage()); //TODO 나중에 제대로 처리
        }

        return SigninResponse.of(refreshToken.getRefreshToken(), accessToken);
    }

}
