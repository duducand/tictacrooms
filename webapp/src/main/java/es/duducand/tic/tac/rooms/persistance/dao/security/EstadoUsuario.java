package es.duducand.tic.tac.rooms.persistance.dao.security;

import java.io.Serializable;

import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;

public interface EstadoUsuario extends Serializable {
	public EstadoUsuario checkLogin(String login);
	public Usuario getUsuario();
	public boolean hasPermission(Object permiso, Object objeto);
    public boolean hasPermission(Object permission, String type, Serializable id);
}
