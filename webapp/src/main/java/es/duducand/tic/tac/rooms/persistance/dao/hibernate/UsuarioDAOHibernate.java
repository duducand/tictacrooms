package es.duducand.tic.tac.rooms.persistance.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import es.duducand.tic.tac.rooms.persistance.dao.UsuarioDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;

public class UsuarioDAOHibernate extends GenericHibernateDAO<Usuario, Integer> 
                                 implements UsuarioDAO {

	/**
     * 
     */
    private static final long serialVersionUID = 6653236054540664179L;


    
	public Usuario getUsuarioByLogin(String login) {
	    
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = getHibernateTemplate().findByCriteria(criteria);
		
		Usuario usuario = null;
		if (usuarios.size() > 0) {
			usuario = usuarios.get(0);
		} 
		return usuario;
	}
	
}
