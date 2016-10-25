/*
 * snackbar 1.0 25 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.infrastructure.security.UserSecurity;
import br.com.hyperclass.snackbar.infrastructure.security.UserSecurityRepository;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 25 de out de 2016
 */
public class UserPersistence implements UserSecurityRepository {
	
	private final List<UserDetails> userSecurity;
	
	public UserPersistence() {
		super();
		this.userSecurity = new ArrayList<>();
	}
	
	public void addUserSecurity(final UserDetails userDetails){
		this.userSecurity.add(userDetails);
	}

	@Override
	public void add(UserDetails userSecurity) {
	}

	@Override
	public void remove(UserDetails userSecurity) {
	}

	@Override
	public UserSecurity loadUserByUsername(String name) {
		return null;
	}

	@Override
	public List<UserDetails> all() {
		return null;
	}

}
