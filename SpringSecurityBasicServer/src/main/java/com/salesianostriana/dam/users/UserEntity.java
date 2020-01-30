package com.salesianostriana.dam.users;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6189678452627071360L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	private String avatar;
	
	private String fullName;
	
	private String email;

	private String role;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder.Default
	private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
	}

	/**
	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	/**
	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
