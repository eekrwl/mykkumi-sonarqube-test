package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.Login;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.dto.MeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/users/me")
    public ResponseEntity<MeResponse> getMe(@Login User user) {
        if (user == null) { //TODO service에서 해야될 일이 이 예외처리밖에 없어보이는데 service로 보내는 것이 나을지??
            throw new CommonException(ErrorCode.INVALID_TOKEN, "로그인을 해주세요", "올바른 토큰이 아니거나 토큰이 존재하지 않습니다.");
        }
        MeResponse meResponse = MeResponse.of(user);
        return ResponseEntity.ok(meResponse);
    }
}
