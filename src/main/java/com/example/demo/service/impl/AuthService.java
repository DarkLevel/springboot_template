package com.example.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthService {

  @Value("${secret}")
  private static String SECRET;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    return (extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String generateToken(String userName) {
    return createToken(new HashMap<>(), userName);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSignKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private String createToken(Map<String, Object> claims, String userName) {
    return Jwts.builder()
        .claims().empty().add(claims).and()
        .subject(userName)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getSignKey()).compact();
  }

  private SecretKey getSignKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
  }

}
