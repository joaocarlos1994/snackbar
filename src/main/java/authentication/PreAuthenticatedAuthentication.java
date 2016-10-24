/*
 * @(#)PreAuthenticatedAuthenticationWithToken.java 1.0 04/12/2015
 *
 * Copyright (c) 2015, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import br.com.hyperclass.snackbar.domain.user.User;
import br.com.hyperclass.snackbar.infrastructure.security.UserRole;

/**
 * A classe <code>PreAuthenticatedAuthentication</code> representa um usuário
 * preautenticado na aplicação. Ela difere da classe
 * <code>PreAuthenticatedAuthenticationToken</code> provida pelo Spring no fato
 * de que ela não precisa de senha nos métodos construtores.
 *
 * @author Roberto Perillo
 * @version 1.0 04/12/2015
 */
public class PreAuthenticatedAuthentication extends PreAuthenticatedAuthenticationToken {

    private static final long serialVersionUID = 1L;

    public PreAuthenticatedAuthentication(final UserRole userRole) {
        super(userRole, Arrays.asList(new DefaultGrantedAuthority(userRole)));
        setAuthenticated(true);
    }

    @Override
    public User getPrincipal() {
        return (User) super.getPrincipal();
    }

    public boolean isAdmin() {
        final Collection<GrantedAuthority> authorities = getAuthorities();
        final Iterator<GrantedAuthority> it = authorities.iterator();
        while (it.hasNext()) {
            final DefaultGrantedAuthority auth = (DefaultGrantedAuthority) it.next();
            if (auth.isAdmin()) {
                return true;
            }
        }
        return false;
    }
}