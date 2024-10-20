package com.example.demo.security;
/*
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthentificationFilter jwtAuthentificationFilter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/f_all/**")
                        .permitAll()
                        .anyRequest()
                        .permitAll()
               // les requetes qui commencent par "auth" (connection, inscription) peuvent etre faites par n'importe qui
               // les autres requetes peuvent etre faites que si on est authentifié
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
*/
