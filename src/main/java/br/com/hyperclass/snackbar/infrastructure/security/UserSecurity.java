/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.security;

import java.security.Principal;
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

public class UserSecurity implements UserDetails, Principal {
	
	private static final long serialVersionUID = 1L;
	
	private final String login;
	private final String password;
	private final Role role;
	private final List<Role> roles;
	
	public UserSecurity(final String login, final String password, final Role role) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.roles = new ArrayList<>();
	}
	
	public void addUserRole(final Role role){
		this.addUserRole(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		final Role admin = new Role(PerfilAuthority.ADMIN);
		final Role counter = new Role(PerfilAuthority.COUNTER);
		
		addUserRole(admin);
		addUserRole(counter);
		
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	public Role getRole() {
		return role;
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

	@Override
	public String getName() {
		return login;
	}

}
