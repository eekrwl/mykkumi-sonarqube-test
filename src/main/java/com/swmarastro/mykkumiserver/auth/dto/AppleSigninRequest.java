package com.swmarastro.mykkumiserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppleSigninRequest extends SigninRequest {
    private String authorizationCode;
}
