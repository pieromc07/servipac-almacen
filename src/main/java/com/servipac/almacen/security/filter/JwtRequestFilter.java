package com.servipac.almacen.security.filter;

import com.servipac.almacen.security.common.JwtUtils;
import com.servipac.almacen.security.common.ResponseUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String CREDENTIALS = "";
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtUtils.isValidToken(authorizationHeader)) {
            try {
                setAuthentication(authorizationHeader);
                filterChain.doFilter(request, response);
            } catch (JwtException e) {
                ResponseUtils.setCustomForbiddenResponse(response);
            }
        } else {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        }
    }

    private void setAuthentication(String authorizationHeader) {
        List<GrantedAuthority> authorities = jwtUtils.getAuthorities(authorizationHeader);

        if (authorities == null || authorities.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron permisos para el usuario");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                jwtUtils.getUsernameFromToken(authorizationHeader),
                CREDENTIALS,
                authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
