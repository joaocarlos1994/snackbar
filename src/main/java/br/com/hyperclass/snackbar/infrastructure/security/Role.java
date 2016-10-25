/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;

import br.com.hyperclass.snackbar.domain.user.PerfilAuthority;
import br.com.hyperclass.snackbar.domain.user.User;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private final PerfilAuthority perfilAuthority;
	
	public Role(final PerfilAuthority perfilAuthority) {
		this.perfilAuthority = perfilAuthority;
	}
	
	@Override
	public String getAuthority() {
		return perfilAuthority.name();
	}
	
	public boolean isAdmin(){
		if(perfilAuthority.name().equals("ADMIN")) return true;
		return false;
	}

}
