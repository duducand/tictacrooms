package es.duducand.tic.tac.rooms.persistance.dao.security.impl;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class UsuarioVoter implements AccessDecisionVoter {
	
	private PermissionEvaluator permissionEvaluator;
	
	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> config) {
		if (object instanceof MethodInvocation) {
			MethodInvocation invocation = (MethodInvocation) object;
			for (Object argument: invocation.getArguments()) {
				boolean anyGranted = false;
				for (ConfigAttribute attribute: config) {
					if (permissionEvaluator.hasPermission(authentication, argument, attribute.getAttribute())) {
						anyGranted = true;
					}
				}
				if (!anyGranted) {
					return ACCESS_DENIED;
				}
			}
			return ACCESS_GRANTED;
		} else {
			return ACCESS_ABSTAIN;
		}
	}

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
