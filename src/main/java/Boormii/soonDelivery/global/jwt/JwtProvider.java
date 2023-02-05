package Boormii.soonDelivery.global.jwt;

import Boormii.soonDelivery.global.exception.ErrorCode;
import Boormii.soonDelivery.global.exception.FilterException;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.dto.token.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    @Value("${spring.jwt.secret}")
    private static String SECRET_KEY;
    private static final String AUTHORITIES_KEY = "auth";
    private static final Long ACCESS_TOKEN_VALID_TIME = 10 * 60 * 1000L; // 10min
    private static final Long REFRESH_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L; // 24hours

    private Key key;

//    public JwtProvider() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Base64로 인코딩
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
    public JwtProvider(@Value("${spring.jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateToken(Authentication authentication) {
        long now = (new Date()).getTime();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date accessTokenExpireTime = new Date(now + ACCESS_TOKEN_VALID_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
//                .claim("email", member.getEmail())
                .setExpiration(accessTokenExpireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Date refreshTokenExpireTime = new Date(now + REFRESH_TOKEN_VALID_TIME);
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new TokenDto(accessToken, refreshToken, REFRESH_TOKEN_VALID_TIME);
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken); // 토큰 복호화
        if(claims.get(AUTHORITIES_KEY) == null){
             throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
         UserDetails principal = new User(claims.getSubject(), "", authorities);
         return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new FilterException(ErrorCode.INVALID_JWT_SIGN);
        } catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
            throw new FilterException(ErrorCode.EXPIRED_JWT);
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new FilterException(ErrorCode.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e){
            log.info("올바른 JWT 토큰이 아닙니다.");
            throw new FilterException(ErrorCode.INVALID_JWT);
        } catch (Exception e){
            throw new FilterException(ErrorCode.UNKNOWN_ERROR);}
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
