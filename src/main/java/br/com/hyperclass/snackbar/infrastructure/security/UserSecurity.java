/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hyperclass.snackbar.domain.user.PerfilAuthority;
import br.com.hyperclass.snackbar.domain.user.User;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */

public class UserSecurity implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final String login;
	private final String password;
	private final List<UserRole> userRoles;
	
	public UserSecurity(String login, String password) {
		super();
		this.login = login;
		this.password = password;
		this.userRoles = new ArrayList<>();
	}
	
	public void addUserRole(final UserRole userRole){
		this.addUserRole(userRole);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		addUserRole(new UserRole(new User(PerfilAuthority.ADMIN)));
		addUserRole(new UserRole(new User(PerfilAuthority.COUNTER)));
		return userRoles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
