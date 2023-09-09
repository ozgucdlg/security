package com.alibou.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRETKEY  = "eQ1cH5nUs4prjjAaGAn8DdAhT1JPHARh";

    public String extractUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
