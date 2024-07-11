package com.swmarastro.mykkumiserver.auth.token;

import com.swmarastro.mykkumiserver.auth.jwt.JwtProvider;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.User;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    private final Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(15);
    private final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(90);

    /**
     * 리프레시 토큰으로 엑세스 토큰 생성
     */
    public String createNewAccessToken(String refreshToken) {
        if (!jwtProvider.validToken(refreshToken)) {
            throw new CommonException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.", "리프레시 토큰이 유효하지 않습니다.");
        }
        User user = findByRefreshToken(refreshToken).getUser();
        return jwtProvider.generateToken(user, ACCESS_TOKEN_DURATION);
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CommonException(ErrorCode.TOKEN_EXPIRED,"토큰이 만료되었습니다.", "리프레시 토큰이 만료되었습니다."));
        //TODO 리프레시 토큰이 없음
    }

    /**
     * 리프레시 토큰 생성
     */
    public RefreshToken createRefreshToken(User user) {
        String token = jwtProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        return refreshTokenRepository.save(
                RefreshToken.of(user, token)
        );
    }
}
