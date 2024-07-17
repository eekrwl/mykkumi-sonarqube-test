package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.Login;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.dto.MeResponse;
import com.swmarastro.mykkumiserver.user.dto.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/me")
    public ResponseEntity<MeResponse> getMe(@Login User user) {
        if (user == null) { //TODO service에서 해야될 일이 이 예외처리밖에 없어보이는데 service로 보내는 것이 나을지??
            throw new CommonException(ErrorCode.INVALID_TOKEN, "로그인을 해주세요", "올바른 토큰이 아니거나 토큰이 존재하지 않습니다.");
        }
        MeResponse meResponse = MeResponse.of(user);
        return ResponseEntity.ok(meResponse);
    }

    @PatchMapping("/users")
    public ResponseEntity<MeResponse> updateUser(@Login User loginUser, @Valid @ModelAttribute UpdateUserRequest updateUserRequest) {
        if (loginUser == null) {
            throw new CommonException(ErrorCode.INVALID_TOKEN, "로그인을 해주세요", "올바른 토큰이 아니거나 토큰이 존재하지 않습니다.");
        }
        User user = userService.updateUser(loginUser, updateUserRequest);
        MeResponse meResponse = MeResponse.of(user);
        return ResponseEntity.ok(meResponse);
    }
}
