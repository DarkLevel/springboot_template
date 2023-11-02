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

  public String generateToken(String username, Date issuedAt, Date expiration, String tokenSecret) {
    return createToken(new HashMap<>(), username, issuedAt, expiration, tokenSecret);
  }

  public boolean validateToken(UserDetails userDetails, String token, String tokenSecret) {
    return (extractUsername(token, tokenSecret).equals(userDetails.getUsername())
        && !isTokenExpired(token, tokenSecret));
  }

  public String extractUsername(String token, String tokenSecret) {
    return extractClaim(Claims::getSubject, token, tokenSecret);
  }

  public Date extractExpiration(String token, String tokenSecret) {
    return extractClaim(Claims::getExpiration, token, tokenSecret);
  }

  private <T> T extractClaim(Function<Claims, T> claimsResolver, String token, String tokenSecret) {
    return claimsResolver.apply(extractAllClaims(token, tokenSecret));
  }

  private Claims extractAllClaims(String token, String tokenSecret) {
    return Jwts.parser()
        .verifyWith(getSignKey(tokenSecret))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private Boolean isTokenExpired(String token, String tokenSecret) {
    return extractExpiration(token, tokenSecret).before(new Date());
  }

  private String createToken(Map<String, Object> claims, String username, Date issuedAt, Date expiration,
      String tokenSecret) {
    return Jwts.builder()
        .claims().empty().add(claims).and()
        .subject(username)
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(getSignKey(tokenSecret), Jwts.SIG.HS256).compact();
  }

  private SecretKey getSignKey(String tokenSecret) {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSecret));
  }

}
