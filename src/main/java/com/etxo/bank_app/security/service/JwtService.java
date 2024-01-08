package com.etxo.bank_app.security.service;

import com.etxo.bank_app.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "XzBNR7ZsVdx2jjn6K7MDbDvqVV/uCtAntew+rni1WF1c+1QPyOCyhmK0LJLpScTZ";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    public String extractEmail(String token){
        return getClaims(token).get("email", String.class);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
