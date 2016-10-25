/*
 * @(#)ResponseHeaderAuthenticationListener.java 1.0 05/02/2016
 *
 * Copyright (c) 2016, Embraer. All rights reserved. Embraer S/A
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * @author Roberto Perillo
 * @version 1.0 05/02/2016
 */
//@Component
public class ResponseHeaderAuthenticationListener implements AuthenticationListener {

    private static final long FIVE_HOURS_IN_MILLISECONDS = 60000 * 300;
    private final JWSSigner signer;

    @Autowired
    public ResponseHeaderAuthenticationListener(@Value("${jwt.secret}") final String secret) throws Exception {
        super();
        this.signer = new MACSigner(secret);
    }

    /** {@inheritDoc} */
    @Override
    public void onAuthenticationSuccess(final AuthenticationEvent event) throws IOException {
        
    	final long now = System.currentTimeMillis();
    	
    	final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        		.subject(event.getUsername())
        		.issueTime(new Date(now))
        		.issuer("http://www.voxpmo.com")
        		.expirationTime(new Date(now + FIVE_HOURS_IN_MILLISECONDS))
        		.notBeforeTime(new Date(now))
        		.build();
        
        //claimsSet.setSubject(event.getUsername());
        //claimsSet.setIssueTime(new Date(now));
        //claimsSet.setIssuer("http://www.voxpmo.com");
        //claimsSet.setExpirationTime(new Date(now + FIVE_HOURS_IN_MILLISECONDS));
        //claimsSet.setNotBeforeTime(new Date(now));

        final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        try {
            signedJWT.sign(signer);
        } catch (final JOSEException e) {
            throw new AuthenticationServiceException("The given JWT could not be signed.");
        }

        final HttpServletResponse resp = event.getResponse();
        resp.setHeader("Authorization", String.format("Bearer %s", signedJWT.serialize()));
    }
}