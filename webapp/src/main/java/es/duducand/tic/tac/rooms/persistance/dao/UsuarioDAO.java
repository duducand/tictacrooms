package es.duducand.tic.tac.rooms.persistance.dao;


import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
	public Usuario getUsuarioByLogin(String login);
}
