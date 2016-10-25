/*
 * @(#)JwtClaimsVerifier.java 1.0 9 de out de 2016
 *
 * Copyright (c) 2016, VoxPMO Ltda. All rights reserved. VoxPMO
 * proprietary/confidential. Use is subject to license terms.
 */

package authentication.jwt;

import com.nimbusds.jwt.JWT;

/**
 * Class comments go here...
 *
 * @author Roberto Perillo
 * @version 1.0 9 de out de 2016
 */
public interface JwtVerifier {

    public void verify(final JWT jwt);
}