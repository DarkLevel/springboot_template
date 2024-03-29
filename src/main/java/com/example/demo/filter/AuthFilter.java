package com.example.demo.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.demo.enums.OpenEndpoints;
import com.example.demo.service.impl.CustomUserDetailsService;
import com.example.demo.service.impl.TokenService;
import com.example.demo.utils.Utilities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

  private final String tokenSecret;
  private final HandlerExceptionResolver handlerExceptionResolver;
  private final TokenService tokenService;
  private final CustomUserDetailsService userDetailsService;

  public AuthFilter(@Value("${tokenSecret}") String tokenSecret, HandlerExceptionResolver handlerExceptionResolver,
      TokenService tokenService, CustomUserDetailsService userDetailsService) {
    this.tokenSecret = tokenSecret;
    this.handlerExceptionResolver = handlerExceptionResolver;
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String authHeader = request.getHeader("Authorization");
      String token = null;
      String username = null;

      if (!Utilities.startsWith(request.getRequestURI(),
          Arrays.asList(OpenEndpoints.values()).stream().map(e -> e.getValue()).toList())) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          token = authHeader.substring(7);
          username = tokenService.extractUsername(token, tokenSecret);
        } else {
          throw new BadCredentialsException("Unauthorized");
        }
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (tokenService.validateToken(userDetails, token, tokenSecret)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      handlerExceptionResolver.resolveException(request, response, null, e);
    }
  }

}
