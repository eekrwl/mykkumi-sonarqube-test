package com.swmarastro.mykkumiserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoSigninRequest extends SigninRequest{

    private String refreshToken;
    private String accessToken;
}
