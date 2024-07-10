package com.swmarastro.mykkumiserver.chaewan.token;

import com.swmarastro.mykkumiserver.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", updatable = false)
    private Long id;

    private Long userId;
    private String refreshToken;
    private LocalDateTime token_expiry;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
