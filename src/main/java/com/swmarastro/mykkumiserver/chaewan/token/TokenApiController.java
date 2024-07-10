//package com.swmarastro.mykkumiserver.chaewan.token;
//
//import com.swmarastro.mykkumiserver.auth.token.TokenService;
//import com.swmarastro.mykkumiserver.chaewan.token.dto.CreateAccessTokenRequest;
//import com.swmarastro.mykkumiserver.chaewan.token.dto.CreateAccessTokenResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1")
//public class TokenApiController {
//    private final TokenService tokenService;
//
//    @PostMapping("/token")
//    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
//            (@RequestBody CreateAccessTokenRequest request) throws IllegalAccessException {
//        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new CreateAccessTokenResponse(newAccessToken));
//    }
//}
