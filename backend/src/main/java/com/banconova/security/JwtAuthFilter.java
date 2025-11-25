package com.banconova.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwt;
    private final UserDetailsService uds;

    public JwtAuthFilter(JwtService jwt, UserDetailsService uds){
        this.jwt=jwt; this.uds=uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String h = request.getHeader("Authorization");
        if(h!=null && h.startsWith("Bearer ")){
            String token = h.substring(7);
            try{
                Claims c = jwt.parse(token).getBody();
                String username=c.getSubject();
                var ud = uds.loadUserByUsername(username);
                var auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch(Exception ignored){}
        }
        filterChain.doFilter(request,response);
    }
}
