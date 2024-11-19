package com.eventus.eventus.security;

import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.repository.UserRepository;
import com.eventus.eventus.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;
	@Autowired
	private UserRepository repository;

  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService){
    this.jwtTokenProvider = jwtTokenProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }
    String jwt = authHeader.substring(7);
    // String username = jwtTokenProvider.extractUsername(jwt);
		String id = jwtTokenProvider.extractId(jwt);
    UserModel user = null;
    if(id != null && SecurityContextHolder.getContext().getAuthentication() == null){
      user = repository.findById(Integer.valueOf(id)).get();
    }
    UsernamePasswordAuthenticationToken authToken = null;
    if(jwtTokenProvider.isTokenValid(jwt, user)){
      authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    }
    SecurityContextHolder.getContext().setAuthentication(authToken);
    filterChain.doFilter(request, response);
  }
}
