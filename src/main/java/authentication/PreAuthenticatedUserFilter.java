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
 * A classe <code>PreAuthenticatedUserFilter</code> é o filtro responsável por
 * recuperar o token do usuário no header de uma requisição e retorná-lo, para
 * que posteriormente as implementações de <code>AuthenticationManager</code> de
 * cada aplicação possam recuperar os dados do usuário no Embraer Account, a
 * partir do token, e colocar o usuário no contexto de segurança do Spring.
 * 
 * Caso o filtro verifique a ausência do token no header da requisição, será
 * retornado no response o HttpStatus com o código 403.
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