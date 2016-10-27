/*
 * @(#)IssuerReferenceClaimsVerifier.java 1.0 9 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication.jwt;

import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 9 de out de 2016
 */
@Component
public class IssuerReferenceClaimsVerifier implements JwtVerifier {

    /** {@inheritDoc} */
    @Override
    public void verify(final JWT jwt) {
        final ReadOnlyJWTClaimsSet claims;
        try {
            claims = jwt.getJWTClaimsSet();
        } catch (final ParseException exception) {
            throw new JwtTokenException("Invalid JWT.");
        }
        final String issuerReference = "http://localhost:8080/snackbar/";
        final String issuer = claims.getIssuer();
        if (!issuerReference.equals(issuer)) {
            throw new JwtTokenException("Invalid issuer");
        }
    }
}