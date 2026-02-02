package com.rohith.userservice.util;

import com.rohith.userservice.Security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class AuthUtils {
    @Value("${jwt.secret}")
    private String secrete ;


    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secrete.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(CustomUserDetails user){
//       return Jwts.builder()         //to build
//                .Setsubject(user.getId().toString())  // here we get the id which is in UserEntity class and Id is Long so we use toString()
//                .claim("email",user.getEmail())   //in claim we have to add user details
//                .claim("roles", Set.of("ADMIN","USER"))  // if you define any role then you can use it like this
//                .issuedAt(new Date())       // every token has a issueAt date
//                .expiration(new Date(System.currentTimeMillis()+1000*60))   // also has an expiration time, such as expiring 1 minute after creation
//                .signWith(getSecretKey())     //signwith the key which is generated
//                .compact();      // all of above need to compact

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles",user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(getSecretKey())
                .compact();
    }

    public Claims generateClaimsFromToken(String token){
        Claims claims= Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims ;

    }

    public Integer generateUserIdFromClaims(String token){
        Claims claims=generateClaimsFromToken(token);

        return Integer.valueOf(claims.getSubject());
    }
}
