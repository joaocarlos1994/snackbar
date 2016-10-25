/*
 * @(#)ReferenceDateClaimsVerifier.java 1.0 9 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication.jwt;

import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;

/**
 * Class comments go here...
 * 
 * @author Roberto Perillo
 * @version 1.0 9 de out de 2016
 */
@Component
public class ReferenceDateClaimsVerifier implements JwtVerifier {

    /** {@inheritDoc} */
    @Override
    public void verify(final JWT jwt) {
        final JWTClaimsSet claims;
        try {
            claims = jwt.getJWTClaimsSet();
        } catch (final ParseException exception) {
            throw new JwtTokenException("Invalid JWT.");
        }
        final Date referenceTime = new Date();
        final Date expirationTime = claims.getExpirationTime();

        if (expirationTime == null || expirationTime.before(referenceTime)) {
            throw new JwtTokenException("The token is expired");
        }
    }
}