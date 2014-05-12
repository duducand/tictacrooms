package es.duducand.tic.tac.rooms.persistance.dao.security.impl;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AfterInvocationProvider;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class UsuarioAfterInvocationProvider implements AfterInvocationProvider {
	
	private PermissionEvaluator permissionEvaluator;
	
	public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}
	

	public Object decide(Authentication authentication, Object object, Collection<ConfigAttribute> config, Object returned)
			throws AccessDeniedException {
		if (object instanceof MethodInvocation) {
			boolean anyGranted = false;
			for (ConfigAttribute attribute: config) {
				anyGranted |= permissionEvaluator.hasPermission(authentication, returned, attribute.getAttribute());
			}
			if (anyGranted) {
				return returned;
			} else {
				throw new AccessDeniedException("Acceso denegado");
			}
		} else {
			return returned;
		}
	}


    public boolean supports(ConfigAttribute arg0) {
        return true;
    }


    public boolean supports(Class<?> arg0) {
        return true;
    }
	
}
