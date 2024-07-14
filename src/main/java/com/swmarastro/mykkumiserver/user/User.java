package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.OAuthProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID uuid;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String email;
    private String introduction;
    private String profileImage;

    @Enumerated(value = EnumType.STRING)
    private OAuthProvider provider;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    public static User of(OAuthProvider provider, String email) {
        return User.builder()
                .provider(provider)
                .email(email)
                .build();
    }
}
