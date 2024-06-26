package com.example.newspeed.jwt;


import com.example.newspeed.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String ACCESS_TOKEN_HEADER = "ACCESS_TOKEN_HEADER";
    public static final String REFRESH_TOKEN_HEADER = "REFRESH_TOKEN_HEADER";

    // Access Token 만료시간 설정 (10초)
    public final long ACCESS_TOKEN_EXPIRATION = 60 * 60 * 1000L; // 1시간
    // Refresh Token 만료기간 설정(1시간)
    public final long REFRESH_TOKEN_EXPIRATION = 60 * 60 * 1000L; //1시간

    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @Value("${jwt.secret}")
    private String secret;
    private Key key;


    // 키 init 하는 메서드 구현해야함
    //    생성자가 만들어진후 한번만 실행됨
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secret);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //토큰 생성 Access, Refresh
    public String generateToken(String username, Long expires, String tokenType) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder().setSubject(username)
                        .claim(AUTHORIZATION_HEADER, tokenType)
                        .setExpiration(new Date(date.getTime() + expires))
                        .setIssuedAt(date)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }
    // JWT 토큰 substring
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    public void addJwtToHeader(HttpServletResponse response, String token, String headerName) {
        response.setHeader(headerName, token);
    }


    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }






    //사용자에게서 토큰을 가져오기
    public String getAccessTokenFromRequest(HttpServletRequest req) {
        return getTokenFromRequest(req, AUTHORIZATION_HEADER);
    }
    //리프레시 토큰은 유저에게서 가져오기
    public String getRefreshTokenFromRequest(User user) {
        return user.getRefreshToken();
    }

    //HttpServletRequest 에서 Cookie Value  JWT 가져오기
    // 쿠키가 아닌 헤더로 변경해야함
    public String getTokenFromRequest(HttpServletRequest req, String headerName){
        String token = req.getHeader(headerName);
        if (token != null && !token.isEmpty()) {
            return token;
        }
        return null;
    }

    // 토큰 검증
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }

    }

}

