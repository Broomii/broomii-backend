package Boormii.soonDelivery.global.jwt;

import Boormii.soonDelivery.global.exception.FilterException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtProvider jwtProvider;


    // 실제 필터링 로직은 doFilterInternal에 들어감.
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Request Header에서 토큰 꺼내기
        String jwt = resolveToken(request);
        if (jwt == null) {
            System.out.println("jwt 토큰이 존재하지 않습니다.");
        }

        // Token validation 검증
        try {
            jwtProvider.validateToken(jwt);

            // 정상 토큰이면 해당 토큰을 Authentication를 가져와서 SecurityContext에 저장
            if (StringUtils.hasText(jwt)) {
                Authentication authentication = jwtProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (FilterException exception) {
            request.setAttribute("exception", exception.getErrorCode());
        }


        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}