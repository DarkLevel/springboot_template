package com.example.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

  public String generateToken(String username, Date issuedAt, Date expiration, String secret) {
    return createToken(new HashMap<>(), username, issuedAt, expiration, secret);
  }

  public boolean validateToken(UserDetails userDetails, String token, String secret) {
    return (extractUsername(token, secret).equals(userDetails.getUsername()) && !isTokenExpired(token, secret));
  }

  public String extractUsername(String token, String secret) {
    return extractClaim(Claims::getSubject, token, secret);
  }

  public Date extractExpiration(String token, String secret) {
    return extractClaim(Claims::getExpiration, token, secret);
  }

  private <T> T extractClaim(Function<Claims, T> claimsResolver, String token, String secret) {
    return claimsResolver.apply(extractAllClaims(token, secret));
  }

  private Claims extractAllClaims(String token, String secret) {
    return Jwts.parser()
        .verifyWith(getSignKey(secret))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private Boolean isTokenExpired(String token, String secret) {
    return extractExpiration(token, secret).before(new Date());
  }

  private String createToken(Map<String, Object> claims, String username, Date issuedAt, Date expiration,
      String secret) {
    return Jwts.builder()
        .claims().empty().add(claims).and()
        .subject(username)
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(getSignKey(secret), Jwts.SIG.HS256).compact();
  }

  private SecretKey getSignKey(String secret) {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

}
