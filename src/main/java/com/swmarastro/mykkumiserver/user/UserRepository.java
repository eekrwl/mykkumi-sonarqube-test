package com.swmarastro.mykkumiserver.user;

import com.swmarastro.mykkumiserver.auth.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByEmailAndProvider(String email, OAuthProvider provider);
}
