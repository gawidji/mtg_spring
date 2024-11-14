package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthentificationFilter jwtAuthentificationFilter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	// Appel de la méthode filterChain de Spring Security qui va intercepter les requetes http et les filtrer
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        		// Desactivation de csrf pour que les requetes ne soient plus dans des sessions indépendantes (stateless)
        		//le serveur retient l'identité de l'user après son auth 
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/f_all/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
               // les requetes qui commencent par "auth" (connection, inscription) peuvent etre faites par n'importe qui
               // les autres requetes peuvent etre faites que si on est authentifié
                )
                // Utilise un AuthenticationProvider personnalisé pour gérer l'authentification
                .authenticationProvider(authenticationProvider)
                // Ajoute un filtre personnalisé pour le jwt token
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
*/
