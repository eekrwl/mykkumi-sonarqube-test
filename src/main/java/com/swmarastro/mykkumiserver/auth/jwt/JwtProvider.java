package com.swmarastro.mykkumiserver.auth.jwt;

import com.swmarastro.mykkumiserver.chaewan.JwtProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public boolean validToken(String accessToken) {
        try {
            getJwtParser().parseClaimsJws(accessToken);
            return true;
        } catch (Exception e) {
            return false;
            //throw new ApplicationException(ErrorCode.UNAUTHORIZED_USER);
        }
    }

    public Long getSubject(String token) {
        return Long.valueOf(getJwtParser().parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public String generateToken(Long userId, Duration expireTime) {
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + expireTime.toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
    }

    private Key getSigningKey() {
        String encoded = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
        log.info("인코딩된 키 " + encoded);
        return Keys.hmacShaKeyFor(encoded.getBytes());
    }
}
