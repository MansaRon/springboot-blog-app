package com.springboot.blog.security;

import com.springboot.blog.exceptions.BlogAPIException;
import com.springboot.blog.exceptions.ClientException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Thendo
 * @date 2024/01/23
 */
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        // Date currentDate = new Date();
        // Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        Instant currentDate = Instant.now();
        Instant expireDate = currentDate.plus(Long.parseLong(jwtExpirationDate), ChronoUnit.SECONDS);

        Date current = Date.from(currentDate);
        Date expire = Date.from(expireDate);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(current)
                .setExpiration(expire)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // get username from JWT token
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        //return extractClaim(token, Claims::getSubject);
    }

    // Check is token is valid
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException malformedJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT Token has expired");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT Token is unsupported");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT string is empty/null");
        }
//        final String username = getUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenValid(token);
    }

}
