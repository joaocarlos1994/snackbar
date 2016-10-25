/*
 * snackbar 1.0 25 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 25 de out de 2016
 */
public interface UserSecurityRepository {
	
	void add(final UserDetails userSecurity);
	void remove(final UserDetails userSecurity);
	UserSecurity loadUserByUsername(final String name);
	List<UserDetails> all();
	
}
