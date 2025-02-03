package com.easyaccess.app.common.providers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
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

  private Claims getClaims(String token) {
    return Jwts.parser()
      .verifyWith(getSecretKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public String generateToken(String email, String role, Long userId) {
    return Jwts.builder()
      .subject(email)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
      .claim("userId", userId)
      .claim("role", role)
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

  public String extractRole(String token) {
    return getClaims(token).get("role", String.class);
  }

  public Long extractUserId(String token) {
    return getClaims(token).get("userId", Long.class);
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    return null;
  }
}
