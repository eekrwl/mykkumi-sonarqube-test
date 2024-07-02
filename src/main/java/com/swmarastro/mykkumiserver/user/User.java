package com.swmarastro.mykkumiserver.user;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String email;
    private String introduction;
    private String profileImage;
}
