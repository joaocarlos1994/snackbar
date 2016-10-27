/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.user;

/**
 * A classe <code>User</code> representa um usuário da aplicação. Ela define em seu construtor um
 * tipo de perfil do usuário.
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class UserSnack {
	
	private final String name;
	private final String password;
	private final PerfilAuthority perfilAuthority;

	public UserSnack(final String name, String password, final PerfilAuthority perfilAuthority) {
		super();
		this.name = name;
		this.password = password;
		this.perfilAuthority = perfilAuthority;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public PerfilAuthority getPerfilAuthority() {
		return perfilAuthority;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
