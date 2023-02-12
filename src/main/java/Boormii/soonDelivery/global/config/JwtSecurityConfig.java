package Boormii.soonDelivery.global.config;

import Boormii.soonDelivery.global.jwt.JwtFilter;
import Boormii.soonDelivery.global.jwt.JwtProvider;
import Boormii.soonDelivery.global.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtFilter customFilter = new JwtFilter(jwtProvider);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
