/*
 * @(#)JwtTokenException.java 1.0 4 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package com.voxpmo.opportunity.authentication.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 4 de out de 2016
 */
public class JwtTokenException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public JwtTokenException(final String message) {
        super(message);
    }
}