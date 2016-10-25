/*
 * @(#)JwtAuthenticationManager.java 1.0 3 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication.jwt;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.voxpmo.opportunity.authentication.PreAuthenticatedAuthentication;
import com.voxpmo.opportunity.domain.user.UserRepository;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 3 de out de 2016
 */
@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    private final UserRepository repository;
    private final List<JwtVerifier> verifiersList = new ArrayList<>();

    @Autowired
    public JwtAuthenticationManager(final UserRepository repository) {
        super();
        this.repository = repository;
    }

    /** {@inheritDoc} */
    @Override
    public Authentication authenticate(final Authentication auth) throws AuthenticationException {
        final String token = String.valueOf(auth.getPrincipal()).substring(6).trim();
        final JWT jwt;
        final ReadOnlyJWTClaimsSet claims;

        try {
            jwt = JWTParser.parse(token);
            claims = jwt.getJWTClaimsSet();
        } catch (final ParseException exception) {
            throw new JwtTokenException("The given JWT could not be parsed.");
        }

        for (final JwtVerifier verifier : verifiersList) {
            verifier.verify(jwt);
        }

        final String username = claims.getSubject();
        return new PreAuthenticatedAuthentication(repository.getByUsername(username));
    }

    @Resource
    public void setVerifiersList(final List<JwtVerifier> verifiersList) {
        this.verifiersList.addAll(verifiersList);
    }
}