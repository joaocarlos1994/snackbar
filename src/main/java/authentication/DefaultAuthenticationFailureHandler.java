/*
 * @(#)DefaultAuthenticationFailureHandler.java 1.0 09/12/2015
 *
 * Copyright (c) 2015, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * A classe <code>DefaultAuthenticationFailureHandler</code> representa a
 * implementação de <code>AuthenticationFailureHandler</code> que trata
 * possíveis problemas de autenticação na aplicação.
 *
 * @author Roberto Perillo
 * @version 1.0 09/12/2015
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String JSON = "{\"errorCode\": %d, message: \"%s\"}";

    /** {@inheritDoc} */
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().print(String.format(JSON, HttpStatus.UNAUTHORIZED.value(), exception.getMessage()));
    }
}