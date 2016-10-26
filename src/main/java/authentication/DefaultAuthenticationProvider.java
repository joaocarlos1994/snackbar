/*
 * @(#)DefaultAuthenticationProvider.java 1.0 3 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.domain.user.UserRepository;



/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 3 de out de 2016
 */
@Component
public class DefaultAuthenticationProvider extends DaoAuthenticationProvider {

    private final UserRepository repository;

    @Autowired
    public DefaultAuthenticationProvider(final UserRepository repository, final UserDetailsService service, final PasswordEncoder encoder) {
        super();
        setUserDetailsService(service);
        setPasswordEncoder(encoder);
        this.repository = repository;
    }

    @Override
    protected Authentication createSuccessAuthentication(final Object principal, final Authentication authentication, final UserDetails userDetails) {
        return new PreAuthenticatedAuthentication(repository.getByUsername(((UserDetails) principal).getUsername()));
    }
}