package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.Login;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import com.swmarastro.mykkumiserver.user.dto.MeResponse;
import com.swmarastro.mykkumiserver.user.dto.UpdateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<MeResponse> getMe(@Login User user) {
        if (user == null) { //TODO service에서 해야될 일이 이 예외처리밖에 없어보이는데 service로 보내는 것이 나을지?? -> 카테고리 등 추가 가능성, 서비스로 빼기
            throw new CommonException(ErrorCode.INVALID_TOKEN, "로그인을 해주세요", "올바른 토큰이 아니거나 토큰이 존재하지 않습니다.");
        }
        MeResponse meResponse = MeResponse.of(user);
        return ResponseEntity.ok(meResponse);
    }

    @PatchMapping("/users")
    public ResponseEntity<MeResponse> updateUser(@Login User loginUser, @Valid @ModelAttribute UpdateUserRequest updateUserRequest) {
        //TODO 닉네임 유효성 검사에서 걸렸을 때, 에러메시지 만들어주기
        if (loginUser == null) {
            throw new CommonException(ErrorCode.INVALID_TOKEN, "로그인을 해주세요", "올바른 토큰이 아니거나 토큰이 존재하지 않습니다.");
        } //이거 filter에서 걸리게하고 얘 지우면 되는데, 필터에서 걸린애는 CommonException으로 못받을것.. 찾아보기..
        User user = userService.updateUser(loginUser, updateUserRequest);
        MeResponse meResponse = MeResponse.of(user);
        return ResponseEntity.ok(meResponse);
    }
}
