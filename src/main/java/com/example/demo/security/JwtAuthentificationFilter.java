package com.example.demo.security;

/*
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter{

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Si le header du token n'est pas nul et commence par "Bearer "
		final String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			
		final String jwt = authHeader.substring("Bearer ".length());
		final String email = jwtService.extractUsername(jwt);
		
		
		// Si le mail récupéré n'est pas null
		if (email == null || SecurityContextHolder.getContext().getAuthentication() !=null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		
		// Si le token récupéré est valide
		if(!jwtService.isTokenValid(jwt, userDetails)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		// On retourne alors le contenu du token (UserDetails & authorities) pour l'insérer dans SecurityContextHolder, une class qu'on peut appeler partout dans le projet
		 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                userDetails,
	                null,
	                userDetails.getAuthorities()
	        );
	        authToken.setDetails(
	                new WebAuthenticationDetailsSource().buildDetails(request)
	        );
	        SecurityContextHolder.getContext().setAuthentication(authToken);
	        filterChain.doFilter(request, response);
	    }
		
		
		
	}
*/
