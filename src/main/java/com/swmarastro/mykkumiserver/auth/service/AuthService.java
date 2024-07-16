package com.swmarastro.mykkumiserver.auth.service;

import com.swmarastro.mykkumiserver.auth.OAuthProvider;
import com.swmarastro.mykkumiserver.auth.dto.SigninRequest;
import com.swmarastro.mykkumiserver.auth.dto.SigninResponse;
import com.swmarastro.mykkumiserver.auth.token.TokenService;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.User;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoService kakaoService;
    private final UserService userService;
    private final TokenService tokenService;

    public SigninResponse signin(SigninRequest request, OAuthProvider oAuthProvider) {
        //사용자 이메일 받아오기
        String email = getEmail(request.getAccessToken(), oAuthProvider);
        //유저 찾기
        User user = getUserByEmailAndProvider(email, oAuthProvider);
        //우리 서비스 토큰 발급
        String refreshToken = tokenService.createRefreshToken(user);
        String accessToken = null;
        try {
            accessToken = tokenService.createNewAccessToken(refreshToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다. 잠시 후 다시 시도해주세요", "access token 생성에 실패했습니다.");
        }
        return SigninResponse.of(refreshToken, accessToken);
    }

    User getUserByEmailAndProvider(String email, OAuthProvider oAuthProvider) {
        //사용자 이미 가입된 사용자인지 구분하기
        Optional<User> userOptional = userService.getUserByEmailAndProvider(email, oAuthProvider);
        if (userOptional.isPresent()) { //가입된 사용자
            return userOptional.get();
        } else { //신규가입, 사용자 회원가입 시키기
            return userService.saveUser(OAuthProvider.KAKAO, email);
        }
    }

    String getEmail(String accessToken, OAuthProvider oAuthProvider) {
        if (oAuthProvider == OAuthProvider.KAKAO) {
            return kakaoService.getKakaoInfo(accessToken);
        }
        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다. 다시 시도해주세요.", "OAuth2 사용자 정보를 가져오는데 실패했습니다.");
    }
}
