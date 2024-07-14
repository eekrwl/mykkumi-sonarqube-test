package com.swmarastro.mykkumiserver.auth.token;

import com.swmarastro.mykkumiserver.auth.jwt.JwtProvider;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.User;
import com.swmarastro.mykkumiserver.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    private final Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(15);
    private final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(90);

    /**
     * refresh token으로 refresh token, access token 재발급
     */
    public AuthTokensDTO createNewTokens(String refreshToken) {
        if (!isValidToken(refreshToken)) {
            throw new CommonException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.", "유효하지 않은 토큰입니다.");
        }
        //refresh token 발급
        User user = userService.getUserByUuid(jwtProvider.getSubject(refreshToken));
        String newRefreshToken = createRefreshToken(user);

        //access token 발급
        String newAccessToken = createNewAccessToken(user, newRefreshToken);
        return AuthTokensDTO.of(newRefreshToken, newAccessToken);
    }

    /**
     * refresh token으로 access token 생성
     */
    private String createNewAccessToken(User user, String refreshToken) {
        return jwtProvider.generateToken(user, ACCESS_TOKEN_DURATION);
    }

    /**
     * refresh token 생성
     */
    private String createRefreshToken(User user) {
        UUID tokenUuid = UUID.randomUUID();
        //user의 refresh token 생성
        String token = jwtProvider.generateToken(user, REFRESH_TOKEN_DURATION, tokenUuid);
        //DB에 저장 - TODO 업데이트 칠건지? 새로 만들건지? -> 일단 새로 만드는 것으로 구현
        Date expiry = jwtProvider.getExpiry(token);
        refreshTokenRepository.save(RefreshToken.of(user, token, expiry, tokenUuid));
        return token;
    }

    /**
     * 유효한 refresh token인지, 사용자는 실제로 존재하는지 검증
     */
    public boolean isValidToken(String refreshToken) {
        //유효한 토큰인지 확인
        if(!jwtProvider.validToken(refreshToken)) {
            throw new CommonException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.", "유효하지 않은 토큰입니다.");
        }

        //claim에서 refresh token의 uuid 가져오기
        UUID uuid = jwtProvider.getUuidFromClaim(refreshToken);

        //DB에 존재하는지 확인
        refreshTokenRepository.findByUuid(uuid)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.", "유효하지 않은 토큰입니다."));
        //토큰에서 유저 uuid 추출
        String Uuid = jwtProvider.getSubject(refreshToken);
        //해당 유저 존재하는지 확인
        User user = userService.getUserByUuid(Uuid);
        return true;
    }

}
