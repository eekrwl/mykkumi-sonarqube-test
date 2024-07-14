package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND, "유저가 존재하지 않습니다.", "해당 uuid의 유저가 존재하지 않습니다."));

    }
}