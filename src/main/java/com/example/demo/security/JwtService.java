/*
package com.example.demo.security;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    final static private SignatureAlgorithm alg = SignatureAlgorithm.HS256;
    // génération d'un algo 
    
    private final static SecretKey SECRET_KEY = generateSecretKey();
    // génération d'une clé secrete

    private static SecretKey generateSecretKey() {
        return Keys.secretKeyFor(alg);
    }
    // association de la clé et de l'algo 

    public String generateToken (UserDetails userDetails){
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken (UserDetails userDetails, Map<String, String> extraClaims){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))
                .signWith(SECRET_KEY, alg)
                .compact();
    }
    
    
	Méthode de conception du token d'authentification

	le token contient : 
	setClaims = personnalise le token en y ajoutant des données user supplémentaires
	setSubject = le résultat de la méthode getUserName redéfini dans user
	setIssuedAt =  la date de création du token 
	setExpiration = la date d'expiration du token 
	signWith = association du token à la clé secrète et algorithme
	
    
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    // Atteste de la validité du token 

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // Extrait l'userName qui pourra ensuite etre exploité par les autres fichiers du projet 
    
    
    
    // Les méthodes suivantes servent juste à faire fonctionner isTokenValid et extractUsername
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
*/


