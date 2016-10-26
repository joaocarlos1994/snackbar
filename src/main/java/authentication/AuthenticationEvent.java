/*
 * @(#)AccountAuthenticationEvent.java 1.0 05/02/2016
 *
 * Copyright (c) 2016, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * A classe <code>AccountAuthenticationEvent</code> representa o evento de
 * autenticação realizada com sucesso na aplicação. Pelo fato de que os
 * <i>listeners</i> desse evento podem lidar com a requisição HTTP, instâncias
 * dessa classe também tem os objetos que representam a requisição e a resposta
 * HTTP.
 *
 * @author Roberto Perillo
 * @version 1.0 05/02/2016
 */
public class AuthenticationEvent extends AuthenticationSuccessEvent {

    private static final long serialVersionUID = 1L;
    private final HttpServletResponse response;

    public AuthenticationEvent(final HttpServletResponse response, final Authentication authentication) {
        super(authentication);
        this.response = response;
    }

    @Override
    public PreAuthenticatedAuthentication getAuthentication() {
        return (PreAuthenticatedAuthentication) super.getAuthentication();
    }

    public String getUsername() {
        return getUser().getName();
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    private Principal getUser() {
        //return getAuthentication().getPrincipal();
    	return getAuthentication();
    }
}