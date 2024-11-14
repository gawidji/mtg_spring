package com.example.demo.securityMalek;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Configuration de l'encodage du password

@Component
public class ConfigurePasswordEncoder implements PasswordEncoder {
	
	// Méthode de hashage du password
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(15));
		// Récupère le password en txt
		// Puis le hash (plus le nombre est élevé plus le hashage est complexe)
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}
	

}
