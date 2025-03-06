package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// Specifie que le fichier est un fichier de configuration
@Configuration

// Ajoute les fonctionnalités de spring security
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
    JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ConfigurePasswordEncoder passwordEncoder;
	
	@Bean 
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	// Appel de la méthode filterChain de Spring Security qui va intercepter les requetes http et les filtrer
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
    	
    	// Desactivation de csrf pour que les requetes ne soient plus dans des sessions indépendantes 
		//le serveur retient l'identité de l'user après son auth (identification stateless)
    	
    return	httpSecurity.csrf(csrf -> csrf.disable())
    	.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/f_all/**").permitAll()
                .requestMatchers("/f_user/**").permitAll()
                .requestMatchers("/f_admin/**").permitAll()
                .anyRequest()
                .authenticated()

        )
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
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
