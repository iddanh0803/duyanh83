package com.springboot.demoquanlysieuthi.security;

import com.springboot.demoquanlysieuthi.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtils {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    // Generate Token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(extraClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getKey())
                .compact();
    }

    // Get Claims from token
    private Claims extractAllClaims(String token) {
        Jws<Claims> jws = Jwts.parser()
                .verifyWith(getKey())
                .build().parseSignedClaims(token);
        return jws.getPayload();
    }

    public Collection<GrantedAuthority> extractAuthority(String token) {
        Collection<GrantedAuthority> authorityCollection = new ArrayList<>();
        Claims claims = extractAllClaims(token);
        List<Map<String,String>> authorities = (List<Map<String,String>>) claims.get("authorities");
        for (Map<String,String> map: authorities){
            String s = map.get("authority");
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(s);
            authorityCollection.add(simpleGrantedAuthority);
        }
        return authorityCollection;
    }


    // Get username from token
    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    // Get Expiration from token
    private Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    // Check token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Check token is valid
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Build key jwt
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
