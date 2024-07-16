package com.swmarastro.mykkumiserver.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swmarastro.mykkumiserver.auth.KakaoProperties;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final String BEARER = "Bearer ";
    private final KakaoProperties kakaoProperties;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 카카오 엑세스 토큰으로 사용자 정보 받아오기
     */
    public String getKakaoInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", BEARER + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(kakaoProperties.getUserInfoUri(), HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return parseUserInfo(response.getBody());
            }
            throw new CommonException(ErrorCode.INVALID_TOKEN, "토큰이 유효하지 않습니다.", "해당 카카오 토큰으로 유저 정보를 가져올 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to get Kakao user info: {}", e.getMessage(), e);
            throw new CommonException(ErrorCode.INVALID_TOKEN, "토큰이 유효하지 않습니다.", "해당 카카오 토큰으로 유저 정보를 가져올 수 없습니다.");
        }
    }

    private String parseUserInfo(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.path("kakao_account").path("email").asText("");
        } catch (JsonProcessingException e) {
            log.error("Failed to parse Kakao user info: {}", e.getMessage(), e);
            throw new RuntimeException("Kakao API error: Invalid JSON format", e);
        }
    }
}
