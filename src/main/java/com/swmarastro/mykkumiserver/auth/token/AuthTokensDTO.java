package com.swmarastro.mykkumiserver.auth.token;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthTokensDTO {

    private String refreshToken;
    private String accessToken;

    public static AuthTokensDTO of(String refreshToken, String accessToken) {
        return AuthTokensDTO.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
}
