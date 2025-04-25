package com.onggiyonggi.global.auth;

import com.onggiyonggi.domain.auth.domain.Token;
import com.onggiyonggi.domain.member.domain.Role;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class JwtProvider {

    private final Key key;
    private final long accessTokenDuration;
    private final long refreshTokenDuration;
    private final CustomUserDetailService customUserDetailService;


    public JwtProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-duration}") long accessTokenDuration,
            @Value("${jwt.refresh-token-duration}") long refreshTokenDuration,
        CustomUserDetailService customUserDetailService) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenDuration = accessTokenDuration;
        this.refreshTokenDuration = refreshTokenDuration;
        this.customUserDetailService = customUserDetailService;
    }

    public Token generateToken(String id, Role role) {
        Date now = new Date();
        Date accessTokenExpiry = new Date(now.getTime() + (accessTokenDuration));
        String accessToken = Jwts.builder()
                .setSubject(id)
                .claim("role", role.getKey())
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Date refreshTokenExpiry = new Date(now.getTime() + (refreshTokenDuration));
        String refreshToken = Jwts.builder()
                .setSubject(id)
                .claim("role", role.getKey())
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("role") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UserDetails principal = customUserDetailService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);

        /*Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        return authentication;*/
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.info("SignatureException");
            throw new JwtException(Status.JWT_INVALID.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("ExpiredJwtException");
            throw new JwtException(Status.JWT_EXPIRED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            throw new JwtException(Status.JWT_INVALID.getMessage());
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUsernameFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User)) {
            throw new GeneralException(Status.UNAUTHORIZED);
        }
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

}

