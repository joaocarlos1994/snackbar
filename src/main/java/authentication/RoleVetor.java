/*
 * snackbar 1.0 27 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package authentication;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 27 de out de 2016
 */
@Component
public class RoleVetor implements AccessDecisionVoter<Object> {
	
	@Override
	public boolean supports(final ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null) && attribute.getAttribute().startsWith("ROLE_")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		final String username = authentication.getName();
		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				final String stringedAtribute = attribute.getAttribute();
				final String shortedAtribute = attribute.getAttribute().substring(stringedAtribute.indexOf("_")+1, stringedAtribute.length());
				if (shortedAtribute.equalsIgnoreCase(username)) {
					return ACCESS_GRANTED;
				}
			}
		}
		return result;
	}

	

}
