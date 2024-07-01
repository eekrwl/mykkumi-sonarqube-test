package com.swmarastro.mykkumiserver.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListResponse {

    private List<PostDto> posts;
    private String cursor;

    public static PostListResponse of(List<PostDto> posts, String cursor) {
        return PostListResponse.builder()
                .posts(posts)
                .cursor(cursor)
                .build();
    }
}
