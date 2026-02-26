package com.ayahathout.spring_security_with_jwt.configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DurationFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    // Extract all claims (payload) from the JWT
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

    // Extract a specific claim from the JWT
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Extract the username (sub) from the JWT
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Generate JWT token with extra claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setExpiration(Date.from(Instant.now().plus(2, DurationFormat.Unit.DAYS.asChronoUnit())))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .signWith(getSecretKey())
                .compact();
    }

    // Generate JWT token without extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Extract the expiration data from the JWT
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Check whether the token is expired
    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    // Validate the JWT token
    public Boolean isValidToken(String token, UserDetails userDetails) {
        // Check whether the username sent the token is the same username of the current user
        final String usernameInToken = getUsernameFromToken(token);
        // The token is valid when the username sent the token is the same username of the current user && its expiration data is after the current date
        return usernameInToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
