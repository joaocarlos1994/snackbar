/*
 * @(#)DefaultUserDetails.java 1.0 1 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.domain.user.UserRepository;
import br.com.hyperclass.snackbar.domain.user.UserSnack;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 1 de out de 2016
 */
@Component
public class DefaultUserDetails implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public DefaultUserDetails(final UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
		final UserSnack userSnack = userRepository.getByUsername(name);
		return new User(userSnack.getName(), userSnack.getPassword(), getAuthorities(userSnack));
	}

	private Collection<GrantedAuthority> getAuthorities(final UserSnack userSnack) {

		final DefaultGrantedAuthority defaultGrantedAuthority = new DefaultGrantedAuthority(userSnack.getPerfilAuthority());
		final List<GrantedAuthority> authorities = new ArrayList<>(1);
		authorities.add(defaultGrantedAuthority);

		return authorities;
	}

}