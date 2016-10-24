/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;

import br.com.hyperclass.snackbar.domain.user.User;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
public class UserRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	public UserRole(final User user) {
		this.user = user;
	}
	
	@Override
	public String getAuthority() {
		return user.getPerfilAuthority();
	}
	
	public boolean isAdmin(){
		if(user.getPerfilAuthority().equals("ADMIN")) return true;
		return false;
	}

}
