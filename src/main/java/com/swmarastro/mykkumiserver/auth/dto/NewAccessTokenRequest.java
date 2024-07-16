package com.swmarastro.mykkumiserver.auth.dto;

import lombok.Getter;

@Getter
public class NewAccessTokenRequest {

    private String refreshToken;
}
