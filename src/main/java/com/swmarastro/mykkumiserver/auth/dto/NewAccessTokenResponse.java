package com.swmarastro.mykkumiserver.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewAccessTokenResponse {

    private String accessToken;

    public static NewAccessTokenResponse of(String accessToken) {
        return NewAccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
