package com.swmarastro.mykkumiserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SigninRequest {

    private String refreshToken;
    private String accessToken;
    private String authorizationCode;
}
