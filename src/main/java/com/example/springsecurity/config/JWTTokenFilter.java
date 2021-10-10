package com.example.springsecurity.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager manager;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().contains("login") || request.getServletPath().contains("deny");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		request.getHeader("test");
		System.out.println("request.getServletPath() = " + request.getServletPath() + " through the filter");
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("john", "12345",
				null);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		manager.authenticate(authentication);
		filterChain.doFilter(request, response);
	}

}
