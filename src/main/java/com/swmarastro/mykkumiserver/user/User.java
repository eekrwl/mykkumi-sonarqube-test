package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.oauth.OAuthProvider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Setter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column
    private String nickname;
    @Column(nullable = false)
    private String email;
    private String introduction;
    private String profileImage;

    @Enumerated(value = EnumType.STRING)
    private OAuthProvider provider;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "pw";
    }

    @Override
    public String getUsername() {
        return "name";
    }

    public static User of(OAuthProvider provider, String email) {
        return User.builder()
                .provider(provider)
                .email(email)
                .build();
    }
}
