package com.swmarastro.mykkumiserver.auth.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppleUserInfoDto {
    //TODO email 제외 쓸 일 없으면 지우기
    private String id;
    private String token;
    private String email;
}
