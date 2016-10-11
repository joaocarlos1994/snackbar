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
public class User {
	
	private final PerfilAuthority perfilAuthority;

	public User(PerfilAuthority perfilAuthority) {
		super();
		this.perfilAuthority = perfilAuthority;
	}

	public PerfilAuthority getPerfilAuthority() {
		return perfilAuthority;
	}
	
	
	
}
