package com.swmarastro.mykkumiserver.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(updatable = false, nullable = false, unique = true, insertable = false)
    private String uuid;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String email;
    private String introduction;
    private String profileImage;
}
