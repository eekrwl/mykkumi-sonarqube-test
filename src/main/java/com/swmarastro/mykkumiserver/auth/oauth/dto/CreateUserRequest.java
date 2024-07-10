package com.swmarastro.mykkumiserver.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserRequest {
    private String name;
    private String email;
    private String imageUrl;
}
