package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private String secretKey = "bd9744212065d8ee2acd3106fedf67c4ca18b417d6202147c24dc2b653224089";

    public Claims extractClaims(String token) {
    	 return (Claims) Jwts
    			 .parserBuilder()
    			 .setSigningKey(Decoders.BASE64.decode(secretKey))
    			 .build()
    			 .parse(token)
    			 .getBody();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    // Extract roles or claims from JWT
    @SuppressWarnings("unchecked")
	public List<String> extractRoles(String token) {
        Claims claims = extractClaims(token);
        return claims.get("roles", List.class); // Assuming "roles" is part of JWT claims
    }

}
