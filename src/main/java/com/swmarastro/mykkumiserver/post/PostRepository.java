package com.swmarastro.mykkumiserver.post;

import com.swmarastro.mykkumiserver.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //TODO Querydsl로 추후 변경하기
    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.id < :lastId AND p.isDeleted = false ORDER BY p.id DESC limit :limit")
    List<Post> findLatestOrderByIdDesc(Long lastId, Integer limit);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.id < :lastId AND p.isDeleted = false ORDER BY p.id DESC")
    Long countPostsByOrderByIdDesc(Long lastId);
}
