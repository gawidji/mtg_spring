package com.example.demo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.UserActivity;
import com.example.demo.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "deckbuilder")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeckCreator implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pseudo", length = 25, nullable = false, unique = true)
	private String pseudo;
	
	@Column(name = "email", length = 32, nullable = false, unique = true)
	private String email;
	
	@Column(name = "mot_de_passe", length = 500, nullable = false)
	private String password;
	
	@Column(name = "date_inscription")
	@Temporal(TemporalType.DATE)
	private LocalDate dateSign;
	
	@Column(name ="avatar", length = 500 )
	private String avatar;
	
	@Column(name = "activite", nullable = true)
	@Enumerated(EnumType.STRING)
	private UserActivity activity;
	
	@Lob
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
    private List<UserRole> roles = new ArrayList<>();
	

	
	@OneToMany(mappedBy = "deckBuilder", cascade = CascadeType.ALL)
	private Set<Deck> decks;
	// Relation one - user & many - decks
	//mappé par "user" qui se retrouve dans la classe Deck pour générer une relation bi-directionnelle 
	// CascadeTpe.all pour que les opérations de la classe User (persist, remove, merge, etc) soient aussi effectués sur la classe Deck



	// Transforme les roles de l'utilisateur en grantedAuthorities
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole userRole : roles) {
        	  GrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
              grantedAuthorities.add(authority);
		}
		return grantedAuthorities;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}


	
	
	


}
