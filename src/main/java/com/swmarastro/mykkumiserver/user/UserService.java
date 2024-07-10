package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.oauth.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Provider;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User saveUser(OAuthProvider provider, String email) { //TODO 로그인에 필요한 부분 상위객체 하나두고 상속해서 넣어도 좋을듯
        return userRepository.save(User.of(provider, email));
    }
}
