package com.parth.jwttoken.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

   @Value("${application.security.jwt.secret-key}")
   private String SECRET_KEY;

   @Value("${application.security.jwt.expiration}")
   private long jwt_expiration;

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
      final Claims claims = extractAllClaims(token);
      return claimResolver.apply(claims);
   }

   public String generateToken(UserDetails userDetails) {
      return generateToken(new HashMap<>(), userDetails);
   }

   public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
      return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
           .issuedAt(new Date(System.currentTimeMillis()))
           .expiration(new Date(System.currentTimeMillis() + jwt_expiration))
           .signWith(getSignInKey())
           .compact();
   }

   public boolean isTokenValid(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   /**
    * The {@code extractAllClaims} method extracts all the claims from the JWT token.
    * This is done by parsing the JWT.
    * The {@code signInKey} is the signature part of the Jwt, which is used to verify that
    * the user IS who he claims to be.
    *
    * @param token a JWT token
    */
   private Claims extractAllClaims(String token) {
      return Jwts.parser().verifyWith((SecretKey) getSignInKey())
           .build().parseSignedClaims(token).getPayload();
   }

   private Key getSignInKey() {
      byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
   }
}
