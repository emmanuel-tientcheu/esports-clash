package com.example.esport_clash.auth.application.services.jwtservice;

import com.example.esport_clash.auth.domain.model.AuthUser;
import com.example.esport_clash.auth.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConcreteJWTService implements JWTService{
    private final SecretKey secretKey;
    private final long expiration;

    public ConcreteJWTService(String secret, long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    @Override
    public String tokenize(User user) {
        var claims = Jwts.claims()
                .subject(user.getId())
                .add("email", user.getEmail())
                .build();

        var createdAt = LocalDateTime.now();
        var expireAt = createdAt.plusSeconds(expiration);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(
                        Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant())
                )
                .expiration(
                        Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant())
                )
                .signWith(secretKey)
                .compact();
    }

    @Override
    public AuthUser parse(String token) {
        var jwtParser = Jwts.parser().verifyWith(secretKey).build();
        var claims = jwtParser.parseSignedClaims(token).getPayload();

        var id = claims.getSubject();
        var email = claims.get("email", String.class);
        return new AuthUser(id, email);
    }
}
