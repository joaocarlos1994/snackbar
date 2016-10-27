/*
 * @(#)DefaultAuthenticationProvider.java 1.0 3 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.domain.user.UserRepository;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 3 de out de 2016
 */
@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	private final UserRepository repository;

	@Autowired
	public DefaultAuthenticationProvider(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return new PreAuthenticatedAuthentication(repository.getByUsername(authentication.getName()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}