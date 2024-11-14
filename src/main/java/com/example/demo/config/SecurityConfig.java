package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Specifie que le fichier est un fichier de configuration
@Configuration

// Ajoute les fonctionnalités de spring security
@EnableWebSecurity
public class SecurityConfig {
	
	// Appel de la méthode filterChain de Spring Security qui va intercepter les requetes http et les filtrer
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
    	
    	// Desactivation de csrf pour que les requetes ne soient plus dans des sessions indépendantes 
		//le serveur retient l'identité de l'user après son auth (identification stateless)
    	httpSecurity.csrf(csrf -> csrf.disable())
    	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
    	// Retour de l'http configuré
    	return httpSecurity.build();
    }
    
    // Méthode d'encodage du password a appelé dans la méthode de création d'user
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
    	return new BCryptPasswordEncoder();
    }
    
    // Méthode pour sécuriser l'auth lors du login 
    @Bean 
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}
