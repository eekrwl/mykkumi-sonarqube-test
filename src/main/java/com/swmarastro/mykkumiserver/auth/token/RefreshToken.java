package com.swmarastro.mykkumiserver.auth.token;

import com.swmarastro.mykkumiserver.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", updatable = false, nullable = false)
    public Long id;

    @Column(updatable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String refreshToken;
    private Date tokenExpiry;

    @CreatedDate
    private LocalDateTime createdAt;

    public static RefreshToken of(User user, String token, Date expiry, UUID uuid) {
        return RefreshToken.builder()
                .user(user)
                .refreshToken(token)
                .uuid(uuid)
                .tokenExpiry(expiry)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
