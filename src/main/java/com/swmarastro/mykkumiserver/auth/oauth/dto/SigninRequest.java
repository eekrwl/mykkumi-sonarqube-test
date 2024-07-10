package com.swmarastro.mykkumiserver.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SigninRequest {

    private String refreshToken;
    private String accessToken;
    //private LocalDateTime refreshTokenExpiresAt;
    //private LocalDateTime accessTokenExpiresAt;
}
