package com.swmarastro.mykkumiserver.auth.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swmarastro.mykkumiserver.auth.jwt.JwtProvider;
import com.swmarastro.mykkumiserver.auth.oauth.dto.CreateOauthUserRequest;
import com.swmarastro.mykkumiserver.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    @Value("${jwt.access-token-expired-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    private final JwtProvider jwtProvider;

    //카카오 엑세스 토큰으로 사용자 정보 받아오기
    public CreateOauthUserRequest getKakaoInfo(String accessToken) throws JsonProcessingException {
        log.info("여기까지는 오냐..");
        log.info("access token : " + accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_INFO_URL, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            System.out.println("jsonNode: " + jsonNode);

            // 필수 정보
            String email = jsonNode.path("kakao_account").path("email").asText("");

            System.out.println("= = = " + email);

            return new CreateOauthUserRequest(email);
        }
        return null;
    }

    /*
    public String getKakaoAccessToken(String code) throws JsonProcessingException { //인가코드로 카카오 엑세스 토큰 받아오기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", KAKAO_CLIENT_ID);
        data.add("client_secret", KAKAO_CLIENT_SECRET);
        data.add("code", code); // 카카오로부터 받은 인가 코드
        data.add("redirect_uri", KAKAO_REDIRECT_URI); // 카카오로부터 등록한 리다이렉트 URI

        // 요청 객체 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(data, headers);

        // RestTemplate를 이용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String accessToken = jsonNode.get("access_token").asText();

            return accessToken;
        } else {
            return null; //나중에 제대로
        }
    }*/

    public HttpHeaders getLoginHeader(User user) {
        //액세스 토큰 생성 -> 패스에 액세스 토큰을 추가
        String accessToken = jwtProvider.generateToken(user.getId(), Duration.ofMinutes(15));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }
}
