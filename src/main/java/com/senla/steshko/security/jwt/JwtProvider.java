package com.senla.steshko.security.jwt;

import com.senla.steshko.entities.Role;
import com.senla.steshko.exception.TokenValidationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.prefix: Authorization}")
    private String prefix;

    @Value("${jwt.secret: jbgdjkfbgHBKJBKdgbkdfgbdfg54}")
    private String jwtSecret;

    @Value("${jwt.validity: 604800}")
    private String validity;

    public static final String AUTHORIZATION = "Authorization";

    private final UserDetailsService userDetailsService;

    public String createToken(String login, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", getRoles(roles));

        Date start = new Date();
        Date expiration = new Date(start.getTime() + Long.parseLong(validity));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(start)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Set<String> getRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toSet());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        return (bearerToken != null && bearerToken.startsWith(prefix)) ?
                bearerToken.substring(prefix.length()) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenValidationException("Token is invalid!");
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public  String getLogin(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

}