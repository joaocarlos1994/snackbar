/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.infrastructure.security.UserSecurityRepository;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
@Component
public class UserService implements UserDetailsService {
	
	private final UserSecurityRepository userRepository;
	
	@Autowired
	public UserService(final UserSecurityRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.loadUserByUsername(username);
	}

}
