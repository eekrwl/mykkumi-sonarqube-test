package com.swmarastro.mykkumiserver.auth.oauth.service;

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
        ResponseEntity<String> response = restTemplate.exchange(kakaoProperties.getUserInfoUri(), HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                // 필수 정보
                String email = jsonNode.path("kakao_account").path("email").asText("");
                return email;
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("Kakao API error json 형식 안맞음", e);
            }

        }
        //TODO 에러메시지 뭐라고 내려줘야되는지, 이 상황이 토큰이 유효하지 않아서 생긴 문제가 항상 맞을지 모르겠다..
        throw new CommonException(ErrorCode.INVALID_TOKEN, "토큰이 유효하지 않습니다.", "해당 카카오 토큰으로 유저 정보를 가져올 수 없습니다.");
    }
}
