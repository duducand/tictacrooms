package es.duducand.tic.tac.rooms.persistance.dao.security.impl;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import es.duducand.tic.tac.rooms.persistance.dao.security.EstadoUsuario;

public class UsuarioPermissionEvaluator implements PermissionEvaluator {
	private EstadoUsuario estadoUsuario;
	
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public boolean hasPermission(Authentication authentication, Object object, Object permission) {
        return estadoUsuario.hasPermission(permission, object);
    }

    public boolean hasPermission(Authentication authentication, Serializable id, String type, Object permission) {
        return estadoUsuario.hasPermission(permission, type, id);
    }

}
