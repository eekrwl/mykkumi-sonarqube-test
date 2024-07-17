package com.swmarastro.mykkumiserver.user.dto;

import com.swmarastro.mykkumiserver.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeResponse {

    private String nickname;
    private String email;
    private String introduction;
    private String profileImage;

    public static MeResponse of(User user) {
        if (user.getNickname() == null) {
            return MeResponse.builder()
                    .email(user.getEmail())
                    .build();
        }
        return MeResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .profileImage(user.getProfileImage())
                .build();
    }
}

