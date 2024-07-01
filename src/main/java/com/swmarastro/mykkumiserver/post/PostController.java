package com.swmarastro.mykkumiserver.post;

import com.swmarastro.mykkumiserver.post.dto.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 홈화면 무한스크롤 포스트, 일단 모든 카테고리 최신순
     */
    @GetMapping("/posts")
    public ResponseEntity<PostListResponse> getPosts(@RequestParam(required = false) String cursor, @RequestParam(required = false, defaultValue = "5") Integer limit) {
        PostListResponse infiniteScrollPosts = postService.getInfiniteScrollPosts(cursor, limit);
        return ResponseEntity.ok(infiniteScrollPosts);
    }
}
