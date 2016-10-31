/*
 * snackbar 1.0 26 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.user;

/**
 * A <code>UserRepository</code> é uma interface onde que as classes de persistencia
 * deverao implementar para retornar um usuario para aplicacao.
 * 
 * @author João Batista
 * @version 1.0 26 de out de 2016
 */
public interface UserRepository {
	
	/**
	 * O metodo recebe o username de um usuario e retorno sera um objeto 
	 * do SncakBar.
	 * 
	 * @param name username
	 */
	UserSnack getByUsername(final String name);
	
}
