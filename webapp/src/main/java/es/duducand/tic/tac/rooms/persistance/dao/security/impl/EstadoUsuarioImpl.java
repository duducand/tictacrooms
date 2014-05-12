package es.duducand.tic.tac.rooms.persistance.dao.security.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
//import java.util.Set;
//import java.util.TreeSet;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;

import es.duducand.tic.tac.rooms.persistance.dao.UsuarioDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.persistance.dao.security.EstadoUsuario;

public class EstadoUsuarioImpl implements EstadoUsuario {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
     * 
     */
    private static final long serialVersionUID = 9208545374862905499L;
    private UsuarioDAO usuarioDao;
	
	private Usuario usuario;

	
	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public EstadoUsuario checkLogin(String login) {
        if (usuario == null || !usuario.getLogin().equals(login)) {
            this.usuario = usuarioDao.getUsuarioByLogin(login);
        }
        if (usuario == null) {
            throw new AccessDeniedException("Acceso denegado");
        }
        return this;
    }
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public boolean hasPermission(Object permission, Object object) {
		if (object instanceof Usuario) {
			return true;
		} else {
			return true;
		}
	}
	public boolean hasPermission(Object permiso, String tipo, Serializable id) {
        if (Usuario.class.getName().equals(tipo)) {
            return true;// TODO denegar o dar permisos segun el usuario tenga derecho o no //hasPermission(permiso);
        } else {
            return true;
        }
    }
	

}
