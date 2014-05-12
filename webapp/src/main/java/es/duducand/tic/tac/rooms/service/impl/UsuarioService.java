package es.duducand.tic.tac.rooms.service.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.duducand.tic.tac.rooms.persistance.dao.UsuarioDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.persistance.dao.security.EstadoUsuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;
import es.duducand.tic.tac.rooms.service.IUsuarioService;

public class UsuarioService implements IUsuarioService{
	private static final transient long serialVersionUID = 1L;
	
	private EstadoUsuario estadoUsuario;
	private UsuarioDAO usuarioDao;

	
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	
	public Usuario createUsuarioTrabajador() {
	    return crearUsuarioPermisos(false, true, false);
	}
	
	public Usuario createUsuarioSocio() {
        return crearUsuarioPermisos(false, false, true);
    }
	
	private Usuario crearUsuarioPermisos(Boolean administrador,Boolean trabajador,Boolean socio){
	    Usuario usuario = new Usuario();
        usuario.setAdministrador(administrador);
        usuario.setTrabajador(trabajador);
        usuario.setSocio(socio);
        return usuario;
	}
	
	public Usuario getUsuario(Integer id) {
		Usuario usuario = usuarioDao.getById(id, true);
		return usuario;
	}
	
	public void saveUsuario(es.duducand.tic.tac.rooms.persistance.dao.model.Usuario usuario) {
	    usuarioDao.save(usuario);
	}
	
	public void deleteUsuario(Usuario usuario){
		usuario.setFechaBaja(new Date());
		usuario.setUsuarioBaja(estadoUsuario.getUsuario());
		usuarioDao.save(usuario);
	}
	


	public PagedResult<Usuario> getUsuarios(PagedQuery query) {
		return new PagedResult<Usuario>(
				usuarioDao.countByPagedQuery(query),
				query.getStart(),
				usuarioDao.findByPagedQuery(query));
	}
	
	public List<Usuario> getUsuarios() {
		return new ArrayList<Usuario>();
	}

    public void reactivarUsuario(Usuario usuario) {
        usuario.setFechaBaja(null);
        usuario.setUsuarioBaja(null);
        this.saveUsuario(usuario);
    }

    
}
