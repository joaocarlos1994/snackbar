/*
 * @(#)PreAuthenticatedUserFilter.java 1.0 04/12/2015
 *
 * Copyright (c) 2015, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * A classe <code>PreAuthenticatedUserFilter</code> � o filtro respons�vel por
 * recuperar o token do usu�rio no header de uma requisi��o e retorn�-lo, para
 * que posteriormente as implementa��es de <code>AuthenticationManager</code> de
 * cada aplica��o possam recuperar os dados do usu�rio no Embraer Account, a
 * partir do token, e colocar o usu�rio no contexto de seguran�a do Spring.
 * 
 * Caso o filtro verifique a aus�ncia do token no header da requisi��o, ser�
 * retornado no response o HttpStatus com o c�digo 403.
 *
 * @author Roberto Perillo
 * @version 1.0 04/12/2015
 */
public class PreAuthenticatedUserFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final String AUTHORIZATION = "Authorization";

    /** {@inheritDoc} */
    @Override
    protected String getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION) != null ? (String) request.getHeader(AUTHORIZATION) : request.getParameter(AUTHORIZATION);
    }

    /** {@inheritDoc} */
    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        } finally {
            SecurityContextHolder.clearContext();
            final HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session != null) {
                session.removeAttribute("SPRING_SECURITY_CONTEXT");
            }
            //TokenRepository.clear();
        }
    }
}