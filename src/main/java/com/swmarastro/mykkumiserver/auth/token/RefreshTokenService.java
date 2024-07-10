package com.swmarastro.mykkumiserver.auth.token;

import com.swmarastro.mykkumiserver.auth.jwt.JwtProvider;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CommonException(ErrorCode.TOKEN_EXPIRED,"토큰이 만료되었습니다.", "리프레시 토큰이 만료되었습니다."));
    }

    public RefreshToken createRefreshToken(Long userId) {
        String token = jwtProvider.generateToken(userId, Duration.ofDays(90));
        return refreshTokenRepository.save(
                RefreshToken.of(userId, token)
        );
    }

}
