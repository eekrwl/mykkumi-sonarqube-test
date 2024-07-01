package com.swmarastro.mykkumiserver.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostLatestCursor {

    private LocalDateTime startedAt; //TODO 후에 좋아요순 정렬 등을 위해 생성했는데 여기에 두는 것이 맞을까? 당장은 필요가 없다.
    private Long postId;

    public static PostLatestCursor of(LocalDateTime startedAt, Long postId) {
        return PostLatestCursor.builder()
                .postId(postId)
                .startedAt(startedAt)
                .build();
    }
}
