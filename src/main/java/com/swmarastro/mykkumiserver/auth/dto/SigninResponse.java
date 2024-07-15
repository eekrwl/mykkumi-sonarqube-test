package com.swmarastro.mykkumiserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SigninResponse {

    private String refreshToken;
    private String accessToken;

    public static SigninResponse of(String refreshToken, String accessToken) {
        return SigninResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
}
