package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.OAuthProvider;
import com.swmarastro.mykkumiserver.category.UserSubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;
    private String nickname;
    @Column(nullable = false)
    private String email;
    private String introduction;
    private String profileImage;

    @Enumerated(value = EnumType.STRING)
    private OAuthProvider provider;

    @OneToMany(mappedBy = "user")
    private List<UserSubCategory> userSubCategories = new ArrayList<>();

    public static User of(OAuthProvider provider, String email) {
        return User.builder()
                .provider(provider)
                .email(email)
                .uuid(UUID.randomUUID())
                .build();
    }

    //TODO <카테고리> 제외, 연관관계 메서드 사용?? 여기는 연관관계 주인이 아니라서 여기를 바꾼다고 DB에 반영되지는 않는다.
    public void updateUser(String nickname, String introduction, String profileImage) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (introduction != null) {
            this.introduction = introduction;
        }
        if(profileImage != null) {
            this.profileImage = profileImage;
        }
    }
}
