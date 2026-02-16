package com.example.to_do_list.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET= "bhenkar-security-key-12345678912345615456513515465";

    private final Key key= Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8));

    //TOKEN GENERATION
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()+60*60*1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //TOKEN VALIDATION+EMAIL EXTRACT
    public String validateTokenAndGetEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // latest correct method
                .getBody()
                .getSubject();
    }
}
