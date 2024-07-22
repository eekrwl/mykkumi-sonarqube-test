package com.swmarastro.mykkumiserver.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppleProperties {

    @Value("${spring.security.oauth2.client.registration.apple.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.apple.key-id}")
    private String keyId;

    @Value("${spring.security.oauth2.client.registration.apple.team-id}")
    private String teamId;

    @Value("${spring.security.oauth2.client.registration.apple.private-key}")
    private String privateKey;

    @Value("${spring.security.oauth2.client.registration.apple.grant-type}")
    private String grantType;

    @Value("${spring.security.oauth2.client.registration.apple.audience}")
    private String audience;
}
