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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.infrastructure.service.UserService;



/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 3 de out de 2016
 */
@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private BCryptPasswordEncoder encoder;
    private final UserService userService;
    
    @Autowired
	public DefaultAuthenticationProvider(final UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		final String password = authentication.getCredentials().toString();
		
		UserDetails user = userService.loadUserByUsername(username);
		if (encoder.matches(user.getPassword(), password)) {
			Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
			return auth;
		}
		return null;
		//return new PreAuthenticatedAuthentication(authentication.getPrincipal(userService.loadUserByUsername(authentication.getName())));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

  
}