/*
 * @(#)CMSGrantedAuthority.java 1.0 22/03/2016
 *
 * Copyright (c) 2016, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import org.springframework.security.core.GrantedAuthority;

import br.com.hyperclass.snackbar.domain.user.PerfilAuthority;

/**
 * A classe <code>CMSGrantedAuthority</code> representa uma autoridade (em nivel
 * de Spring Security) de um usuario na aplicacao. Um objeto
 * <code>CMSGrantedAuthority</code> contem um objeto <code>Authority</code>, que
 * e o que determina a area e o perfil do usuario.
 *
 * @author Roberto Perillo
 * @version 1.0 22/03/2016
 */
public class DefaultGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private final PerfilAuthority perfilAuthority;

    public DefaultGrantedAuthority(final PerfilAuthority perfilAuthority) {
        super();
        this.perfilAuthority = perfilAuthority;
    }

    /** {@inheritDoc} */
    @Override
    public String getAuthority() {
        return "ROLE_" + perfilAuthority.name();
    }

    public boolean isAdmin() {
        return perfilAuthority.isAdmin(perfilAuthority.name());
    }
}