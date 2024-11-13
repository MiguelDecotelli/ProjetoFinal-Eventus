package com.eventus.eventus.security;

import com.eventus.eventus.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secretKey;
  public String extractUsername(String token){
    return extractClaim(token, Claims::getSubject);
  }
  public <T>T extractClaim(String token, Function<Claims, T> claimsResolver){
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  private Claims extractAllClaims(String token){
    return Jwts
            .parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
  }
  public String generateToken(UserModel user){
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, user);
  }
  private String createToken(Map<String, Object> claims, UserModel user){
    var role = user.getRole();
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUsername())
            .setId(String.valueOf(user.getId()))
            .claim("role", role)
            .setIssuer("api")
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }
  public boolean isTokenValid(String token, UserModel user){
    final String username = extractUsername(token);
    return(username.equals(user.getUsername()) && !isTokenExpired(token));
  }
  private boolean isTokenExpired(String token){
    return extractExpiration(token).before(new Date(System.currentTimeMillis()));
  }
  private Date extractExpiration(String token){
    return extractClaim(token, Claims::getExpiration);
  }
}
