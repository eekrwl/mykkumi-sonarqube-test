package com.swmarastro.mykkumiserver.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "무한스크롤 응답")
@Getter
@Builder
public class PostListResponse {

    private List<PostDto> posts;
    @Schema(description = "Nullable, 무한스크롤 시작 시 비워두면 됨, 이후 서버로부터 응답받은 cursor 값 헤더에 넣어준다.")
    private String cursor;

    public static PostListResponse of(List<PostDto> posts, String cursor) {
        return PostListResponse.builder()
                .posts(posts)
                .cursor(cursor)
                .build();
    }
}
