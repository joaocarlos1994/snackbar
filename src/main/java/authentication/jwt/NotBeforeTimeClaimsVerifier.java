/*
 * @(#)NotBeforeTimeClaimsVerifier.java 1.0 9 de out de 2016
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
public class NotBeforeTimeClaimsVerifier implements JwtVerifier {

    /** {@inheritDoc} */
    @Override
    public void verify(final JWT jwt) {
        final JWTClaimsSet claims;
        try {
            claims = jwt.getJWTClaimsSet();
        } catch (final ParseException exception) {
            throw new JwtTokenException("Invalid JWT.");
        }
        final Date notBeforeTime = claims.getNotBeforeTime();
        if (notBeforeTime == null || notBeforeTime.after(new Date())) {
            throw new JwtTokenException("Not before is after sysdate");
        }
    }
}