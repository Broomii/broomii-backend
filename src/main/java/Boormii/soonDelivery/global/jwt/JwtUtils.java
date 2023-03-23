package Boormii.soonDelivery.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;

@Component
public class JwtUtils {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private Key key;

    public JwtUtils(@Value("${spring.jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    public String getEmailFromRequestHeader(HttpServletRequest request){
        String token = resolveToken(request);
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    public String getEmailFromToken(String token){
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }


    public Boolean checkJwtWithID(HttpServletRequest request, Long _id){
        Long id = getIdFromRequestHeader(request);
        return id.equals(_id);
    }

    private Long getIdFromRequestHeader(HttpServletRequest request){
        String token = resolveToken(request);
        Claims claims = parseClaims(token);
        return Long.parseLong(claims.get("id").toString());
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    private Claims parseClaims(String accessToken){
        System.out.println(this.key);
        try{
            return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(accessToken).getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
