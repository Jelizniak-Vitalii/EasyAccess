package com.easyaccess.app.common.providers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private String jwtExpiration;

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String generateToken(String email) {
    return Jwts.builder()
      .subject(email)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
//      .claim("userId", userId)
//      .claim("role", role)
      .signWith(getSecretKey())
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
        .verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(token);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String extractUsername(String token) {
    Claims claims = Jwts.parser()
      .verifyWith(getSecretKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();

    return claims.getSubject();
  }
}
