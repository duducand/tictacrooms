package es.duducand.tic.tac.rooms.service;

import java.io.Serializable;
import java.util.List;

import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;

public interface IUsuarioService extends Serializable {
	//
	// Métodos
	//
	public Usuario createUsuarioSocio();
	public Usuario createUsuarioTrabajador();
	public Usuario getUsuario(Integer id);
	public void saveUsuario(Usuario usuario);
	public void deleteUsuario(Usuario usuario);
	public PagedResult<Usuario> getUsuarios(PagedQuery query);
	public List<Usuario> getUsuarios();
    public void reactivarUsuario(Usuario usuario);

}
