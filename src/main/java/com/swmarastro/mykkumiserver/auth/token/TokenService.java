package com.swmarastro.mykkumiserver.auth.token;

import com.swmarastro.mykkumiserver.auth.jwt.JwtProvider;
import com.swmarastro.mykkumiserver.user.User;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) throws IllegalAccessException {
        if (!jwtProvider.validToken(refreshToken)) {
            throw new IllegalAccessException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);
        return jwtProvider.generateToken(user.getId(), Duration.ofHours(2));
    }
}
